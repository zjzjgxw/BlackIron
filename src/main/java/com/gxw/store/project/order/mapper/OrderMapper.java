package com.gxw.store.project.order.mapper;

import com.gxw.store.project.order.dto.OrderSearchParam;
import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.entity.OrderItem;
import com.gxw.store.project.order.entity.OrderStatus;

import java.util.List;

public interface OrderMapper {

    void create(Order order);

    void createItem(OrderItem item);

    List<Order> selectOrders(OrderSearchParam searchParam);

    Order getOrder(Long id, Long businessId);

    int update(Order order);
}
