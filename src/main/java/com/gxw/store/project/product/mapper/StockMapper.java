package com.gxw.store.project.product.mapper;


import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.entity.StockSpecification;
import com.gxw.store.project.product.entity.StockSpecificationDetail;


public interface StockMapper {
    void createStockInfo(StockInfo info);

    void createStockSpecification(StockSpecification specification);

    void createStockSpecificationDetail(StockSpecificationDetail detail);

}
