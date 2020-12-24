package com.gxw.store.project.order.mapper;

import com.gxw.store.project.order.dto.OrderSearchParam;
import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.entity.OrderItem;
import com.gxw.store.project.order.entity.OrderStatus;

import java.util.List;

public interface OrderMapper {

    void create(Order order);

    void createItem(OrderItem item);

    List<Long> getOrderIds(OrderSearchParam searchParam);

    List<Order> selectOrders(List<Long> ids);

    Order getOrder(Long id, Long businessId);

    Order getOrderByCode(String code);

    int update(Order order);

    /**
     * 判断是否有用户记录
     * @param orderId
     * @param productId
     * @param userId
     * @return
     */
    int hasOrderOfUser(Long orderId, Long productId, Long userId);
}
