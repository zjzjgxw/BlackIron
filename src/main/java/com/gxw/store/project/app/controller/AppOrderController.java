package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
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
@RequestMapping("app/orders")
public class AppOrderController extends BaseController {

    @Resource
    private OrderService orderService;

    @PostMapping
    public ResponseResult create(@Valid @RequestBody Order order) {
        Long userId = SessionUtils.getUserId();
        order.setUserId(userId);
        order.setBusinessId(SessionUtils.getBusinessId());
        Long id = orderService.create(order);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping
    public ResponseResult getOrders(@RequestParam(required = false) Long id,
                                    @RequestParam(required = false) String code,
                                    @RequestParam(required = false) String telphone,
                                    @RequestParam(required = false) List<Integer> statuses) {
        startPage();
        OrderSearchParam searchParam = new OrderSearchParam();
        searchParam.setUserId(SessionUtils.getUserId());
        searchParam.setId(id);
        searchParam.setCode(code);
        searchParam.setBusinessId(SessionUtils.getBusinessId());
        searchParam.setTelphone(telphone);
        searchParam.setStatuses(statuses);

        List<Order> orders = orderService.selectOrders(searchParam);
        return ResponseResult.success(getDataTable(orders));
    }

    @GetMapping("/payCallback")
    public ResponseResult payCallBack(@RequestParam Long orderId)
    {
        orderService.paid(orderId);
        return ResponseResult.success();
    }

}
