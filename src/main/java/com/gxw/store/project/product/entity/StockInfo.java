package com.gxw.store.project.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class StockInfo {

    private Long id;

    @NotNull(message = "productId 不能为空")
    private Long productId;

    @NotBlank(message = "货币单位不能为空")
    @Length(min = 3,max = 3,message = "货币单位为3字码")
    private String currencyCode;

    @NotNull(message = "价格不能为空")
    private Long price;  // 展示价格

    @NotNull(message = "剩余库存不能为空")
    private Long lastNum; //剩余库存

    private Long saleNum; //总销量

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private List<StockSpecification> specifications; //规格详情

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getLastNum() {
        return lastNum;
    }

    public void setLastNum(Long lastNum) {
        this.lastNum = lastNum;
    }

    public Long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Long saleNum) {
        this.saleNum = saleNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<StockSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<StockSpecification> specifications) {
        this.specifications = specifications;
    }
}
