package com.gxw.store.project.product.service;


import com.gxw.store.project.product.entity.StockInfo;


public interface StockService {

    Long create(StockInfo info);

    StockInfo getStockInfoByProductId(Long productId);

}
