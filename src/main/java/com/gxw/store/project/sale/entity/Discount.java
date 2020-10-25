package com.gxw.store.project.sale.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 限时折扣活动
 */
public class Discount {

    private Long id;
    private Long staffId;
    private Long businessId;
    @NotBlank(message = "促销名不能为空")
    private String name;
    @NotBlank(message = "促销内容说明不能为空")
    private String content;
    @NotNull(message = "促销折扣不能为空")
    private Long discount;
    @NotNull(message = "促销开始时间不能为空")
    private Date startTime;
    @NotNull(message = "促销结束时间不能为空")
    private Date endTime;
    @NotNull(message = "促销类型不饿能够为空")
    private Mode mode;

    private List<Long> products; //指定的商品列表
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

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
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
}
