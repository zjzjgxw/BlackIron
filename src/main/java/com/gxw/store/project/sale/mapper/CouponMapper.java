package com.gxw.store.project.sale.mapper;


import com.gxw.store.project.sale.entity.Coupon;
import com.gxw.store.project.sale.entity.CouponUseStatus;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public interface CouponMapper {
    /**
     * 创建活动
     *
     * @param coupon
     */
    void create(Coupon coupon);

    /**
     * 添加关联的商品
     *
     * @param couponId
     * @param products
     */
    void addProducts(Long couponId, List<Long> products);

    /**
     * 清除关联的商品
     *
     * @param couponId
     */
    void clearProducts(Long couponId);

    /**
     * 获取商户下所有的活动
     *
     * @param businessId
     */
    List<Coupon> selectCoupons(Long businessId);

    /**
     * 获取优惠券详情
     *
     * @param id
     * @return
     */
    Coupon getCoupon(Long id);


    /**
     * 更新活动信息
     *
     * @param coupon
     */
    void update(Coupon coupon);

    void delete(Long id, Long businessId);


    /**
     * 发放优惠券
     *
     * @param id
     * @param userIds
     */
    void send(Long id, Long[] userIds);

    /**
     * 搜索用户优惠券
     *
     * @param userId
     * @param now
     * @param status
     * @return
     */
    LinkedList<Coupon> selectCouponsOfUser(Long userId, Date now, CouponUseStatus status);

    /**
     * 获取用户的一个优惠券
     *
     * @param userId
     * @param couponId
     * @return
     */
    Coupon getCouponInfo(Long userId, Long couponId, Date now, CouponUseStatus status);

    /**
     * 使用优惠券
     * @param userId
     * @param couponId
     * @param orderId
     * @param useTime
     */
    int useCoupon(Long userId, Long couponId, Long orderId, Date useTime);

}
