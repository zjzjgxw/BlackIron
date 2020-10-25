package com.gxw.store.project.sale.service;

import com.gxw.store.project.sale.entity.Coupon;

import java.util.List;

public interface CouponService {
    Long create(Coupon coupon);

    Boolean update(Coupon coupon);

    Boolean delete(Long id, Long businessId);

    List<Coupon> getCoupons(Long businessId);

}
