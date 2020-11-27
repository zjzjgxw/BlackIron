package com.gxw.store.project.sale.service.imp;

import com.gxw.store.project.common.utils.exception.ErrorParamException;
import com.gxw.store.project.sale.entity.Discount;
import com.gxw.store.project.sale.entity.Mode;
import com.gxw.store.project.sale.mapper.DiscountMapper;
import com.gxw.store.project.sale.service.DiscountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiscountServiceImp implements DiscountService {

    @Resource
    private DiscountMapper discountMapper;

    @Override
    @Transactional
    public Long create(Discount discount) {
        discountMapper.create(discount);
        if (discount.getMode() == Mode.PRODUCT) {
            if (discount.getProducts().size() == 0) {
                throw new ErrorParamException("请指定参与活动的商品");
            }
            discountMapper.addProducts(discount.getId(), discount.getProducts());
        }
        return discount.getId();
    }

    @Override
    public List<Discount> getDiscounts(Long businessId) {
        return discountMapper.selectDiscounts(businessId);
    }

    @Override
    @Transactional
    public Boolean update(Discount discount) {
        discountMapper.update(discount);
        if (discount.getMode() == Mode.PRODUCT) {
            if (discount.getProducts().size() == 0) {
                throw new ErrorParamException("请指定参与活动的商品");
            }
            discountMapper.clearProducts(discount.getId());
            discountMapper.addProducts(discount.getId(), discount.getProducts());
        }
        return true;
    }

    @Override
    public Boolean delete(Long id, Long businessId) {
        discountMapper.delete(id, businessId);
        return true;
    }

    @Override
    public Map<Long, Long> getDiscountOfProducts(Long businessId, Long[] productIds) {
        //获取符合要求的全局优惠
        Date date = new Date();
        List<Discount> discounts = discountMapper.getDiscountOfStore(businessId, date);
        Map<Long, Long> result = new HashMap<>();
        for (Discount discount : discounts) {
            if (discount.getMode() == Mode.ALL) {
                for (Long productId : productIds) {
                    if (result.get(productId) == null || (result.get(productId) != null && result.get(productId) >= discount.getDiscount())) {
                        result.put(productId, discount.getDiscount());
                    }
                }
            } else if (discount.getMode() == Mode.PRODUCT) {
                for (Long productId : productIds) {
                    if (discount.getProducts().contains(productId)) {
                        if (result.get(productId) == null || (result.get(productId) != null && result.get(productId) >= discount.getDiscount())) {
                            result.put(productId, discount.getDiscount());
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Long getDiscountOfProduct(Long businessId, Long productId) {
        Date date = new Date();
        List<Discount> discounts = discountMapper.getDiscountOfStore(businessId, date);
        Long currentDiscount = 100L;
        for (Discount discount : discounts) {
            if (discount.getMode() == Mode.ALL) {
                if (currentDiscount >= discount.getDiscount()){
                    currentDiscount = discount.getDiscount();
                }
            } else if (discount.getMode() == Mode.PRODUCT) {
                if (discount.getProducts().contains(productId)) {
                    if (currentDiscount >= discount.getDiscount()) {
                        currentDiscount = discount.getDiscount();
                    }
                }
            }
        }
        return currentDiscount;
    }
}
