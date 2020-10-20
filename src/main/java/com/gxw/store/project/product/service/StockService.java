package com.gxw.store.project.product.service;


import com.gxw.store.project.product.dto.StockUpdateInfo;
import com.gxw.store.project.product.entity.StockInfo;

import java.util.Map;


public interface StockService {

    Long create(StockInfo info);

    StockInfo getStockInfoByProductId(Long productId);

    /**
     * 获取展示价格
     * @param productId
     * @return
     */
    Map<Long,StockInfo> getProductPrice(Long productId);

    Map<Long,StockInfo> getProductPrice(Long[] productIds);

    /**
     * 删除某个库存规格
     * @param id
     * @return
     */
    Boolean deleteStockSpecification(Long id);

    /**
     * 更新库存信息
     * @param info
     * @return
     */
    Boolean updateStockInfo(StockUpdateInfo info);
}
