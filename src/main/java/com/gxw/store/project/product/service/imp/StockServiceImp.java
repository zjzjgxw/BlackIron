package com.gxw.store.project.product.service.imp;

import com.gxw.store.project.product.dto.StockUpdateInfo;
import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.entity.StockSpecification;
import com.gxw.store.project.product.mapper.StockMapper;
import com.gxw.store.project.product.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


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

    @Override
    public Map<Long, StockInfo> getProductPrice(Long productId) {
        return getProductPrice(new Long[]{productId});
    }

    @Override
    public Map<Long, StockInfo> getProductPrice(Long[] productIds) {
        //TODO 后续价格需要整合 折扣，优惠系统

        return stockMapper.getProductPrice(productIds);
    }

    @Override
    public Boolean deleteStockSpecification(Long id) {
        stockMapper.deleteStockSpecification(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateStockInfo(StockUpdateInfo info) {
        stockMapper.updateStockInfo(info);
        for(StockSpecification specification : info.getSpecifications()){
            stockMapper.updateStockSpecificationDetail(specification.getDetail());
        }
        for(StockSpecification specification : info.getNewSpecifications()){
            specification.setStockInfoId(info.getId());
            stockMapper.createStockSpecification(specification);
            specification.getDetail().setStockSpecificationId(specification.getId());
            stockMapper.createStockSpecificationDetail(specification.getDetail());
        }
        return true;
    }


}
