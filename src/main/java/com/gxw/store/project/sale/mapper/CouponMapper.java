package com.gxw.store.project.sale.mapper;


import com.gxw.store.project.sale.entity.Coupon;

import java.util.List;


public interface CouponMapper {
    /**
     * 创建活动
     * @param coupon
     */
    void create(Coupon coupon);

    /**
     * 添加关联的商品
     * @param couponId
     * @param products
     */
    void addProducts(Long couponId, List<Long> products);

    /**
     * 清除关联的商品
     * @param couponId
     */
    void clearProducts(Long couponId);

    /**
     * 获取商户下所有的活动
     * @param businessId
     */
    List<Coupon> selectCoupons(Long businessId);


    /**
     * 更新活动信息
     * @param coupon
     */
    void update(Coupon coupon);

    void delete(Long id, Long businessId);


}
