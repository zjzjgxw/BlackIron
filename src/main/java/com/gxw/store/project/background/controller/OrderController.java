package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.order.dto.OrderSearchParam;
import com.gxw.store.project.order.dto.OrderSendParam;
import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {

    @Resource
    private OrderService orderService;

    /**
     * 发货
     *
     * @return
     */
    @NeedToken
    @PostMapping("/send")
    public ResponseResult send(@RequestBody OrderSendParam param) {
        Long businessId = SessionUtils.getBusinessId();
        orderService.send(businessId, param.getOrderId(), param.getExpressId(), param.getExpressCode());
        return ResponseResult.success();
    }

    /**
     * 修个订单快递信息
     *
     * @param orderId
     * @param expressId
     * @param expressCode
     * @return
     */
    @NeedToken
    @PutMapping("/express")
    public ResponseResult updateExpressInfo(@RequestParam Long orderId, @RequestParam Long expressId, @RequestParam String expressCode) {
        Long businessId = SessionUtils.getBusinessId();
        orderService.updateExpressInfo(businessId, orderId, expressId, expressCode);
        return ResponseResult.success();
    }

    @NeedToken
    @GetMapping
    public ResponseResult getOrders(@RequestParam(required = false) Long id,
                                    @RequestParam(required = false) String code,
                                    @RequestParam(required = false) String telephone,
                                    @RequestParam(required = false) List<Integer> statuses) {
        OrderSearchParam searchParam = new OrderSearchParam();
        searchParam.setId(id);
        searchParam.setCode(code);
        searchParam.setBusinessId(SessionUtils.getBusinessId());
        searchParam.setTelphone(telephone);
        if (statuses != null && !statuses.isEmpty()) {
            searchParam.setStatuses(statuses);
        }
        startPage();

        List<Long> ids = orderService.getOrderIds(searchParam);
        List<Order> orders = orderService.getDetailOfOrders(ids);
        return ResponseResult.success(getDataTable(orders, ids));
    }

    @NeedToken
    @GetMapping("/detail/{id}")
    public ResponseResult getOrder(@PathVariable Long id) {
        Order order = orderService.getOrder(id, SessionUtils.getBusinessId());
        HashMap<String, Order> res = new HashMap<>();
        res.put("info", order);
        return ResponseResult.success(res);

    }
}
