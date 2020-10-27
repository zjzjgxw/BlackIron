package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.order.dto.OrderSearchParam;
import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 发货
     *
     * @param orderId
     * @return
     */
    @PostMapping("/send")
    public ResponseResult send(@RequestParam Long orderId, @RequestParam Long expressId, @RequestParam String expressCode) {
        Long businessId = SessionUtils.getBusinessId();
        orderService.send(businessId, orderId, expressId, expressCode);
        return ResponseResult.success();
    }

    /**
     * 修个订单快递信息
     * @param orderId
     * @param expressId
     * @param expressCode
     * @return
     */
    @PutMapping("/express")
    public ResponseResult updateExpressInfo(@RequestParam Long orderId, @RequestParam Long expressId, @RequestParam String expressCode){
        Long businessId = SessionUtils.getBusinessId();
        orderService.updateExpressInfo(businessId, orderId, expressId, expressCode);
        return ResponseResult.success();
    }

}
