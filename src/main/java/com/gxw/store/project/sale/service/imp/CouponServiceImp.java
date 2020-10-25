package com.gxw.store.project.sale.service.imp;

import com.gxw.store.project.common.utils.exception.ErrorParamException;
import com.gxw.store.project.sale.entity.Coupon;
import com.gxw.store.project.sale.entity.Mode;
import com.gxw.store.project.sale.mapper.CouponMapper;
import com.gxw.store.project.sale.service.CouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CouponServiceImp implements CouponService {

    @Resource
    private CouponMapper couponMapper;


    @Override
    @Transactional
    public Long create(Coupon coupon) {
        couponMapper.create(coupon);
        if (coupon.getMode() == Mode.PRODUCT) {
            if (coupon.getProducts().size() == 0) {
                throw new ErrorParamException("请指定商品");
            }
            couponMapper.addProducts(coupon.getId(), coupon.getProducts());
        }
        return coupon.getId();
    }

    @Override
    public Boolean update(Coupon coupon) {
        couponMapper.update(coupon);
        if (coupon.getMode() == Mode.PRODUCT) {
            if (coupon.getProducts().size() == 0) {
                throw new ErrorParamException("请指定参与活动的商品");
            }
            couponMapper.clearProducts(coupon.getId());
            couponMapper.addProducts(coupon.getId(), coupon.getProducts());
        }else{
            couponMapper.clearProducts(coupon.getId());
        }
        return true;
    }

    @Override
    public Boolean delete(Long id, Long businessId) {
        couponMapper.delete(id, businessId);
        return true;
    }

    @Override
    public List<Coupon> getCoupons(Long businessId) {
        return couponMapper.selectCoupons(businessId);
    }
}
