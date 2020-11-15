package com.gxw.store.project.product.entity;

public class ProductRecommend {

    private Long id;
    private Long businessId;
    private Long productId;
    private Long indexNo;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Long indexNo) {
        this.indexNo = indexNo;
    }
}
