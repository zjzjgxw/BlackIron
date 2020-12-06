package com.gxw.store.project.product.dto;


public class ProductSearchParams {
    private Long businessId;
    private String name;
    private Long categoryId;
    private Long mode;
    private Long statusType;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getMode() {
        return mode;
    }

    public void setMode(Long mode) {
        this.mode = mode;
    }

    public Long getStatusType() {
        return statusType;
    }

    public void setStatusType(Long statusType) {
        this.statusType = statusType;
    }
}
