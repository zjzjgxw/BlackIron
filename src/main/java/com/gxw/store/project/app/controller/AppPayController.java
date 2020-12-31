package com.gxw.store.project.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.service.WeixinPayService;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping("app/pay")
public class AppPayController {

    @Resource
    private OrderService orderService;

    @Resource
    private WeixinPayService weixinPayService;

    @NeedToken
    @GetMapping
    public ResponseResult getPayInfo(@RequestParam Long orderId, @RequestParam String openId) throws IOException {
        Order order = orderService.getOrder(orderId, SessionUtils.getBusinessId());

        HashMap<String,Object> result = weixinPayService.getPayInfo(order,openId);
        return ResponseResult.success(result);
    }


    @PostMapping("/callback")
    public HashMap<String, String> callBack(@RequestBody String res) {
        JSONObject object = JSONObject.parseObject(res);
        String eventType = object.getString("event_type");
        if(eventType != null && eventType.equalsIgnoreCase("TRANSACTION.SUCCESS")){
            JSONObject resource = object.getJSONObject("resource");
            String ciphertext = resource.getString("ciphertext");
            String associatedData = resource.getString("associated_data");
            String nonce = resource.getString("nonce");
            String dataJson = weixinPayService.decrypt(ciphertext,associatedData,nonce);
            JSONObject data = JSONObject.parseObject(dataJson);
            String code = data.getString("out_trade_no");
            boolean success = orderService.paid(code);
            if(success){
                HashMap<String, String> response = new HashMap<>();
                response.put("code", "SUCCESS");
                response.put("message", "");
                return response;
            }else{
                HashMap<String, String> response = new HashMap<>();
                response.put("code", "PAY_ERROR");
                response.put("message", "找不到对应订单");
                return response;
            }
        }else{
            HashMap<String, String> response = new HashMap<>();
            response.put("code", "EVENT_Type_ERROR");
            response.put("message", "微信回调支付状态错误");
            return response;
        }

    }



}
