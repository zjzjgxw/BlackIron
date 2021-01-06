package com.gxw.store.project.sale.service;

import com.gxw.store.project.sale.entity.Coupon;

import java.util.List;

public interface CouponService {
    Long create(Coupon coupon);

    Boolean update(Coupon coupon);

    Boolean delete(Long id, Long businessId);

    List<Coupon> getCoupons(List<Long> ids);

    List<Long> getCouponIds(Long businessId, String name);
    /**
     * 发放优惠券
     *
     * @param id
     * @param userIds
     * @return
     */
    Boolean send(Long id, Long[] userIds);

    /**
     * 获取
     *
     * @param userId
     * @param productIds

     * @return
     */
    List<Coupon> getCouponsOfUser(Long userId, List<Long> productIds);

    /**
     * 获取用户的一个优惠券
     *
     * @param userId
     * @param couponId
     * @return
     */
    Coupon getUseAbleCouponInfo(Long userId, Long couponId);

    Boolean useCoupon(Long userId, Long couponId, Long orderId);
}
