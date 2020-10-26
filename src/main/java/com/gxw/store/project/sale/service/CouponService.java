package com.gxw.store.project.sale.service;

import com.gxw.store.project.sale.entity.Coupon;

import java.util.List;

public interface CouponService {
    Long create(Coupon coupon);

    Boolean update(Coupon coupon);

    Boolean delete(Long id, Long businessId);

    List<Coupon> getCoupons(Long businessId);

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
     * @param productId
     * @param onlyUse
     * @return
     */
    List<Coupon> getCouponsOfUser(Long userId, Long productId, boolean onlyUse);
}
