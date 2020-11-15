package com.gxw.store.project.common.utils;

import com.alibaba.fastjson.JSON;
import com.gxw.store.project.common.utils.exception.WxRequestFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
public class WeiXinUtils {

    @Value("${wx.config.appId}")
    private String appId;
    @Value("${wx.config.secret}")
    private String secret;
    private final static String GRANT_TYPE = "authorization_code";

    @Resource
    private RestTemplate restTemplate;

    public HashMap<String, String> codeToSession(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + this.appId + "&secret=" + this.secret + "&js_code=" + code + "&grant_type=" + GRANT_TYPE;

        String string = restTemplate.getForObject(url, String.class);
        HashMap res = JSON.parseObject(string, HashMap.class);
        if (!res.containsKey("openid")) {
            throw new WxRequestFailedException("errorCode:" + res.get("errcode") + " msg:" + res.get("errmsg"));
        }
        return res;
    }

}
