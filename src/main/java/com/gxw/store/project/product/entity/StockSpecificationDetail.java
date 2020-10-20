package com.gxw.store.project.product.entity;

/**
 * 库存规格详情
 */
public class StockSpecificationDetail {

    private Long id;

    private Long stockSpecificationId;


    private Double price;

    private Long lastNum;

    private String sku;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockSpecificationId() {
        return stockSpecificationId;
    }

    public void setStockSpecificationId(Long stockSpecificationId) {
        this.stockSpecificationId = stockSpecificationId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getLastNum() {
        return lastNum;
    }

    public void setLastNum(Long lastNum) {
        this.lastNum = lastNum;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
