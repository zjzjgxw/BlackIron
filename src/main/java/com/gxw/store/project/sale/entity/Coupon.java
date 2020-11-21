package com.gxw.store.project.sale.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 优惠券活动
 */
public class Coupon {

    private Long id;
    private Long staffId;
    private Long businessId;
    @NotBlank(message = "优惠券名不能为空")
    private String name;
    @NotBlank(message = "优惠券内容说明不能为空")
    private String content;
    @NotNull(message = "抵扣金额不能为空")
    private Long price;
    @NotNull(message = "满减金额不能为空")
    private Long targetPrice;
    @NotNull(message = "活动开始时间不能为空")
    private Date startTime;
    @NotNull(message = "活动结束时间不能为空")
    private Date endTime;
    @NotNull(message = "类型不能够为空")
    private Mode mode;
    @NotNull(message = "剩余张数不能为空")
    private Long num;

    private Boolean canUse;

    private List<Long> products; //指定的商品列表
    private List<CouponUser> users; //优惠券领取用户
    private int deleteFlag;
    private Date createTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }


    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(Long targetPrice) {
        this.targetPrice = targetPrice;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public List<CouponUser> getUsers() {
        return users;
    }

    public void setUsers(List<CouponUser> users) {
        this.users = users;
    }

    public Boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(Boolean canUse) {
        this.canUse = canUse;
    }
}
