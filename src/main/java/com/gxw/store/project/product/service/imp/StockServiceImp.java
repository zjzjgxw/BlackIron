package com.gxw.store.project.product.service.imp;

import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.entity.StockSpecification;
import com.gxw.store.project.product.mapper.StockMapper;
import com.gxw.store.project.product.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StockServiceImp implements StockService {

    @Resource
    private StockMapper stockMapper;

    @Override
    @Transactional
    public Long create(StockInfo info) {

        stockMapper.createStockInfo(info);
        List<StockSpecification> specifications = info.getSpecifications();
        for (StockSpecification specification : specifications) {
            specification.setStockInfoId(info.getId());
            stockMapper.createStockSpecification(specification);
            specification.getDetail().setStockSpecificationId(specification.getId());
            stockMapper.createStockSpecificationDetail(specification.getDetail());
        }

        return info.getId();
    }

    @Override
    public StockInfo getStockInfoByProductId(Long productId) {
        return stockMapper.getStockInfoByProductId(productId);
    }


}
