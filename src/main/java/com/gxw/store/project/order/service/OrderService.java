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
     *
     * @param orderId
     * @return
     */
    Boolean paid(Long orderId);


    /**
     * 发货
     * @param businessId
     * @param orderId
     * @param expressId
     * @param expressCode
     * @return
     */
    Boolean send(Long businessId, Long orderId, Long expressId, String expressCode);

    /**
     * 修个快递信息
     * @param businessId
     * @param orderId
     * @param expressId
     * @param expressCode
     * @return
     */
    Boolean updateExpressInfo(Long businessId, Long orderId, Long expressId, String expressCode);

    /**
     * 判断用户是否拥有某个订单
     * @param orderId
     * @param productId
     * @param userId
     * @return
     */
    Boolean hasOrderOfUser(Long orderId, Long productId, Long userId);

}
