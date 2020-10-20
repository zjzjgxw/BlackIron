package com.gxw.store.project.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class WeiXinUtils {

//    @Resource
//    private WeixinFeign weixinFeign;

    @Value("${wx.config.appId}")
    private String appId;
    @Value("${wx.config.secret}")
    private String secret;
    private final static String GRANT_TYPE = "authorization_code";

    public HashMap<String, String> codeToSession(String code) {
//        String res = weixinFeign.codeToSession(appId, secret, code, GRANT_TYPE);
        String res = "";
        return JSONObject.parseObject(res,HashMap.class);
    }

}
