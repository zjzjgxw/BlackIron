package com.gxw.store.project.sale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gxw.store.project.sale.entity.Coupon;
import com.gxw.store.project.sale.entity.CouponUseStatus;

public class CouponStoreView extends Coupon {

    private boolean received; //是否已领取

    @JsonIgnore
    private CouponUseStatus status;


    public CouponStoreView(Coupon coupon, boolean received) {
        this.received = received;
        this.setId(coupon.getId());
        this.setStaffId(coupon.getStaffId());
        this.setBusinessId(coupon.getBusinessId());
        this.setName(coupon.getName());
        this.setContent(coupon.getContent());
        this.setPrice(coupon.getPrice());
        this.setTargetPrice(coupon.getTargetPrice());
        this.setStartTime(coupon.getStartTime());
        this.setEndTime(coupon.getEndTime());
        this.setMode(coupon.getMode());
        this.setNum(coupon.getNum());
        this.setProducts(coupon.getProducts());
        this.setUsers(coupon.getUsers());
        this.setDeleteFlag(coupon.getDeleteFlag());
        this.setCreateTime(coupon.getCreateTime());
        this.setStatus(coupon.getStatus());
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }
}
