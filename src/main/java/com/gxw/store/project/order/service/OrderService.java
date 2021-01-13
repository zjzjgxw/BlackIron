package com.gxw.store.project.order.service;

import com.gxw.store.project.order.dto.OrderSearchParam;
import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.entity.OrderStat;
import com.gxw.store.project.order.entity.OrderStatTime;

import java.util.Date;
import java.util.List;

public interface OrderService {

    Long create(Order order);

    Long doCreate(Order order);

    List<Long> getOrderIds(OrderSearchParam param);

    List<Order> getDetailOfOrders(List<Long> ids);

    Order getOrder(Long id, Long businessId);

    /**
     * 支付成功
     *
     * @param code
     * @return
     */
    Boolean paid(String code);


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

    /**
     * 完成订单
     * @param orderId
     * @return
     */
    Boolean finished(Long orderId, Long businessId);

    OrderStat stat(Long businessId);

    List<OrderStatTime> statOfTime(Long businessId, Date startTime, Date endTime);

    OrderStatTime statOfToday(Long businessId);

    OrderStatTime statOfYesterday(Long businessId);
}
