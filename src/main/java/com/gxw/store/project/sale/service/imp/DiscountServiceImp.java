package com.gxw.store.project.sale.service.imp;

import com.gxw.store.project.common.utils.exception.ErrorParamException;
import com.gxw.store.project.sale.entity.Discount;
import com.gxw.store.project.sale.mapper.DiscountMapper;
import com.gxw.store.project.sale.service.DiscountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiscountServiceImp implements DiscountService {

    @Resource
    private DiscountMapper discountMapper;

    @Override
    @Transactional
    public Long create(Discount discount) {
        discountMapper.create(discount);
        if(discount.getMode() == 2){
            if(discount.getProducts().size() == 0){
                throw new ErrorParamException("请指定参与活动的商品");
            }
            discountMapper.addProducts(discount.getId(),discount.getProducts());
        }
        return discount.getId();
    }

    @Override
    public List<Discount> getDiscounts(Long businessId) {
        return null;
    }

    @Override
    public Boolean update(Discount discount) {
        return null;
    }

    @Override
    public Boolean delete(Discount discount) {
        return null;
    }
}
