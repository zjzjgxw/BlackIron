package com.gxw.store.project.order.mapper;

import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.entity.OrderItem;

public interface  OrderMapper {

    void create(Order order);

    void createItem(OrderItem item);
}
