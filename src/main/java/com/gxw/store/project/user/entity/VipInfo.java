package com.gxw.store.project.user.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VipInfo {
    private Long id;
    private Long businessId;
    @NotNull
    private Long level;
    @NotBlank
    private String name;
    @NotNull
    private Integer freeExpress;
    private Long discount;
    private Long couponId;

    @NotNull
    private Long consumePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFreeExpress() {
        return freeExpress;
    }

    public void setFreeExpress(Integer freeExpress) {
        this.freeExpress = freeExpress;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getConsumePrice() {
        return consumePrice;
    }

    public void setConsumePrice(Long consumePrice) {
        this.consumePrice = consumePrice;
    }
}
