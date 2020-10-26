package com.gxw.store.project.order.service;

import com.gxw.store.project.order.dto.OrderSearchParam;
import com.gxw.store.project.order.entity.Order;

import java.util.List;

public interface OrderService {

    Long create(Order order);

    Long doCreate(Order order);

    List<Order> selectOrders(OrderSearchParam param);

    /**
     * 支付成功
     * @param orderId
     * @return
     */
    Boolean paid(Long orderId);
}
