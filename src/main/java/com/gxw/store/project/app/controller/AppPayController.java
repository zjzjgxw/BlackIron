package com.gxw.store.project.app.controller;


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

    @GetMapping
    public ResponseResult getPayInfo(@RequestParam Long orderId, @RequestParam String openId) throws IOException {
        Order order = orderService.getOrder(orderId, SessionUtils.getBusinessId());

        HashMap<String,Object> result = weixinPayService.getPayInfo(order,openId);
        return ResponseResult.success(result);
    }


    @PostMapping("/callback")
    public HashMap<String, String> callBack(@RequestBody String res) {

        System.out.println(res);

        HashMap<String, String> response = new HashMap<>();
        response.put("code", "SUCCESS");
        response.put("message", "");
        return response;
    }



}
