package com.gxw.store.project.order.service;

import com.gxw.store.project.order.entity.Order;

public interface OrderService {

    Long create(Order order);

    Long doCreate(Order order);

}
