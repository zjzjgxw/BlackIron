package com.gxw.store.project.product.mapper;


import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.entity.StockSpecification;
import com.gxw.store.project.product.entity.StockSpecificationDetail;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;


public interface StockMapper {
    void createStockInfo(StockInfo info);

    void createStockSpecification(StockSpecification specification);

    void createStockSpecificationDetail(StockSpecificationDetail detail);

    StockInfo getStockInfoByProductId(Long productId);


    @MapKey("productId")
    Map<Long, StockInfo> getProductPrice(Long[] productIds);

    /**
     * 删除某个库存规格
     *
     * @param id 库存规格id
     */
    void deleteStockSpecification(Long id);

    void updateStockInfo(StockInfo info);

    void updateStockSpecificationDetail(StockSpecificationDetail detail);

    /**
     * 修改库存数量
     *
     * @param productId
     * @param num
     */
    void updateStockNum(Long productId, Long num);

    /**
     * 修改总销量
     *
     * @param productId
     * @param num
     */
    void updateStockSaleNum(Long productId, Long num);

    /**
     * 修改库存规格数量
     *
     * @param stockSpecificationId
     * @param num
     */
    void updateSpecificationDetailNum(Long stockSpecificationId, Long num);

}
