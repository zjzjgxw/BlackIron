package com.gxw.store.project.sale.service.imp;

import com.gxw.store.project.common.utils.exception.ErrorParamException;
import com.gxw.store.project.common.utils.exception.NotExistException;
import com.gxw.store.project.common.utils.exception.UnEnoughStockException;
import com.gxw.store.project.sale.entity.Coupon;
import com.gxw.store.project.sale.entity.CouponUseStatus;
import com.gxw.store.project.sale.entity.Mode;
import com.gxw.store.project.sale.mapper.CouponMapper;
import com.gxw.store.project.sale.service.CouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
        } else {
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

    @Override
    @Transactional
    public Boolean send(Long id, Long[] userIds) {
        Coupon coupon = couponMapper.getCoupon(id);
        if (coupon == null) {
            throw new NotExistException("查询不到可用优惠券");
        }
        if (coupon.getNum() < userIds.length) {
            throw new UnEnoughStockException("优惠券数量不足");
        }
        coupon.setNum(coupon.getNum() - userIds.length);
        couponMapper.update(coupon);
        couponMapper.send(id, userIds);
        return true;
    }

    @Override
    public List<Coupon> getCouponsOfUser(Long userId, List<Long> productIds, boolean onlyUse) {
        List<Coupon> coupons;
        if (onlyUse) {
            coupons = couponMapper.selectCouponsOfUser(userId, new Date(), CouponUseStatus.UN_USED);
            if (productIds != null) {
                for (Coupon coupon : coupons) {
                    coupon.setCanUse(true);
                    if (coupon.getMode() == Mode.PRODUCT) {
                        coupon.getProducts().retainAll(productIds);
                        if(coupon.getProducts().size() > 0){
                            coupon.setCanUse(true);
                        }else{
                            coupon.setCanUse(false);
                        }
                    }
                }
            }
        } else {
            coupons = couponMapper.selectCouponsOfUser(userId, null, null);
        }
        return coupons;
    }

    @Override
    public Boolean useCoupon(Long userId, Long couponId, Long orderId) {
        int row = couponMapper.useCoupon(userId, couponId, orderId, new Date());
        if (row == 0) {
            throw new NotExistException("找不到对应的优惠券领取记录");
        }
        return true;
    }

    @Override
    public Coupon getUseAbleCouponInfo(Long userId, Long couponId) {
        return couponMapper.getCouponInfo(userId, couponId, new Date(), CouponUseStatus.UN_USED);
    }
}
