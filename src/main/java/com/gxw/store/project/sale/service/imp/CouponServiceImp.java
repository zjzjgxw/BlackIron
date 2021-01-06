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
import java.util.*;

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
    public List<Coupon> getCoupons(List<Long> ids) {
        if(ids.isEmpty()){
            return Collections.emptyList();
        }
        return couponMapper.selectCoupons(ids);
    }

    @Override
    public List<Long> getCouponIds(Long businessId, String name) {
        return couponMapper.getCouponIds(businessId, name);
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
    public List<Coupon> getCouponsOfUser(Long userId, List<Long> productIds) {
        List<Coupon> coupons;
        coupons = couponMapper.selectCouponsOfUser(userId, null, null);
        for (Coupon coupon : coupons) {
            coupon.setStatus(getCouponUseStatus(coupon, productIds));
        }
        return coupons;
    }

    private CouponUseStatus getCouponUseStatus(Coupon coupon, List<Long> productIds) {
        if (coupon.getUsers().size() == 0) {
            return CouponUseStatus.UN_FIT;
        }
        if (coupon.getUsers().get(0).getStatus() == CouponUseStatus.USED) {
            return CouponUseStatus.USED;
        } else {
            Date now = new Date();
            if (now.getTime() < coupon.getStartTime().getTime()) {
                return CouponUseStatus.UN_START;
            }
            if (now.getTime() > coupon.getEndTime().getTime()) {
                return CouponUseStatus.EXPIRED;
            }
            if (coupon.getMode() == Mode.ALL) {
                return CouponUseStatus.UN_USED;
            } else if (coupon.getMode() == Mode.PRODUCT) { //指定商品模式，则判断是否包含商品
                List<Long> destList = new ArrayList<>();
                destList.addAll(coupon.getProducts());
                destList.retainAll(productIds);
                if (destList.size() > 0) {
                    return CouponUseStatus.UN_USED;
                } else {
                    return CouponUseStatus.UN_FIT;
                }
            }
        }
        return CouponUseStatus.UN_FIT;

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
