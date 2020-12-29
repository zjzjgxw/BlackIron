package com.gxw.store.project.common.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.gxw.store.project.common.service.WeixinPayService;
import com.gxw.store.project.common.utils.exception.ErrorPrepayIdException;
import com.gxw.store.project.order.dto.WeChatPayParams;
import com.gxw.store.project.order.entity.Order;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.HashMap;

@Service
public class WeixinPayServiceImp implements WeixinPayService {

    private static final Logger log = LoggerFactory.getLogger(WeixinPayServiceImp.class);

    //私钥，生成证书时用的私钥
    @Value("${wx.pay.privateKey}")
    private String privateKey;

    @Value("${wx.config.appId}")
    private String appId;


    @Value("${wx.pay.mchId}")
    private String mchId;

    //证书序列表
    @Value("${wx.pay.mchSerialNo}")
    private String mchSerialNo;

    //APIv3秘钥
    @Value("${wx.pay.apiV3Key}")
    private String apiV3Key;

    static final int KEY_LENGTH_BYTE = 32;
    static final int TAG_LENGTH_BIT = 128;

    private static final String PREPAY_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";

    private static final String CALLBACK_URL = "https://b0e99e3ee0db.ngrok.io/app/pay/callback";


    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public HashMap<String, Object> getPayInfo(Order order, String openId) throws IOException {
        Assert.notNull(this.privateKey, "privateKey must not null");
        Assert.notNull(this.appId, "appId must not null");
        Assert.notNull(this.mchId, "mchId must not null");
        Assert.notNull(this.mchSerialNo, "mchSerialNo must not null");
        Assert.notNull(this.apiV3Key, "apiV3Key must not null");

        WeChatPayParams payParams = new WeChatPayParams(this.appId, this.mchId, order.getTitle(), order.getCode(), "", order.getPrice(), "CNY", openId);
        payParams.setNotify_url(CALLBACK_URL);
        String jsonString = JSONObject.toJSONString(payParams);


        // 加载商户私钥（privateKey：私钥字符串）
        PrivateKey merchantPrivateKey = PemUtil
                .loadPrivateKey(new ByteArrayInputStream(this.privateKey.getBytes(StandardCharsets.UTF_8)));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3秘钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(this.mchId, new PrivateKeySigner(this.mchSerialNo, merchantPrivateKey)), this.apiV3Key.getBytes(StandardCharsets.UTF_8));


        // 初始化httpClient
        CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();
        String prepayId = null;
        try {
            HttpPost post = new HttpPost(PREPAY_URL);
            post.addHeader("Content-Type", "application/json");
            post.addHeader("Accept", "application/json");
            StringEntity entity = new StringEntity(jsonString);
            post.setEntity(entity);
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                String body = EntityUtils.toString(httpResponse.getEntity());
                if (statusCode == 200) {
                    JSONObject object = JSONObject.parseObject(body);
                    prepayId = object.getString("prepay_id");
                } else {
                    log.warn("get prepayId failed, statusCode = " + statusCode + ",body = " + body);
                    throw new ErrorPrepayIdException(body);
                }
            } finally {
                httpResponse.close();
            }
        } finally {
            httpClient.close();
        }
        HashMap<String, Object> result = getPayParams(this.appId, prepayId, merchantPrivateKey);
        return result;
    }

    @Override
    public String decrypt(String cipertext, String associatedData, String nonce) {
        try {
            return this.decryptToString(associatedData, nonce, cipertext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String decryptToString(String associatedData, String nonce, String ciphertext)
            throws GeneralSecurityException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKeySpec key = new SecretKeySpec(this.apiV3Key.getBytes(StandardCharsets.UTF_8), "AES");
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData.getBytes(StandardCharsets.UTF_8));
            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), "utf-8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected long generateTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    protected String generateNonceStr() {
        char[] nonceChars = new char[32];

        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(RANDOM.nextInt("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length()));
        }
        return new String(nonceChars);
    }

    private HashMap<String, Object> getPayParams(String appId, String prepayId, PrivateKey privateKey) {
        HashMap<String, Object> result = new HashMap<>();
        long timeStamp = this.generateTimestamp();
        String nonceStr = this.generateNonceStr();
        result.put("timeStamp", timeStamp);
        result.put("nonceStr", nonceStr);
        result.put("package", "prepay_id=" + prepayId);
        result.put("signType", "RSA");
        String message = buildMessage(appId, timeStamp, nonceStr, prepayId);

        String sign = this.sign(message.getBytes(StandardCharsets.UTF_8), privateKey);
        result.put("paySign", sign);
        return result;
    }

    private String sign(byte[] message, PrivateKey privateKey) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(message);
            return Base64.getEncoder().encodeToString(sign.sign());
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException("当前Java环境不支持SHA256withRSA", var3);
        } catch (SignatureException var4) {
            throw new RuntimeException("签名计算失败", var4);
        } catch (InvalidKeyException var5) {
            throw new RuntimeException("无效的私钥", var5);
        }
    }

    private String buildMessage(String appId, long timeStamp, String nonceStr, String prepayId) {
        return appId + "\n" + timeStamp + "\n" + nonceStr + "\n" + "prepay_id=" + prepayId + "\n";
    }
}
