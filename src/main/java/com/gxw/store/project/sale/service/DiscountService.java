package com.gxw.store.project.sale.service;

import com.gxw.store.project.sale.entity.Discount;

import java.util.List;
import java.util.Map;

public interface DiscountService {

    Long create(Discount discount);

    /**
     * 获取限时折扣活动列表
     *
     * @param businessId
     * @return
     */
    List<Discount> getDiscounts(Long businessId);

    Boolean update(Discount discount);

    Boolean delete(Long id, Long businessId);

    /**
     * 获取产品的折扣
     *
     * @param productIds
     * @return
     */
    Map<Long, Long> getDiscountOfProducts(Long businessId, Long[] productIds);
}
