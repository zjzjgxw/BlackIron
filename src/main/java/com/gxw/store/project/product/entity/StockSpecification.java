package com.gxw.store.project.product.entity;

import javax.validation.constraints.NotNull;

/**
 * 库存规格
 */
public class StockSpecification {

    private Long id;

    private Long stockInfoId;

    private String firstName;  //规格一名称
    private String firstValue; //规格一值
    private String secondName; //规格二名称
    private String secondValue; //规格二值

    private StockSpecificationDetail detail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockInfoId() {
        return stockInfoId;
    }

    public void setStockInfoId(Long stockInfoId) {
        this.stockInfoId = stockInfoId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public StockSpecificationDetail getDetail() {
        return detail;
    }

    public void setDetail(StockSpecificationDetail detail) {
        this.detail = detail;
    }
}
