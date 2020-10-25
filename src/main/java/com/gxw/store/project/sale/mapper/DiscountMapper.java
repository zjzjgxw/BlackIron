package com.gxw.store.project.sale.mapper;


import com.gxw.store.project.sale.entity.Discount;
import org.apache.ibatis.annotations.MapKey;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface DiscountMapper {
    /**
     * 创建活动
     * @param discount
     */
    void create(Discount discount);

    /**
     * 添加活动关联的商品
     * @param discountId
     * @param products
     */
    void addProducts(Long discountId, List<Long> products);

    /**
     * 清除活动关联的商品
     * @param discountId
     */
    void clearProducts(Long discountId);

    /**
     * 获取商户下所有的活动
     * @param businessId
     */
    List<Discount> selectDiscounts(Long businessId);


    /**
     * 更新活动信息
     * @param discount
     */
    void update(Discount discount);

    void delete(Long id, Long businessId);


    /**
     * 获取有效的全场优惠
     * @param businessId
     * @return
     */
    List<Discount> getDiscountOfStore(Long businessId, Date now);
}
