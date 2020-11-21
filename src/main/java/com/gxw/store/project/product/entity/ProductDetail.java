package com.gxw.store.project.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gxw.store.project.common.utils.json.Decimal2Serializer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品详情
 */
public class ProductDetail {

    private Long id;
    private Long businessId;

    @NotBlank(message = "商品名不能为空")
    @Length(min = 1, max = 50, message = "商品名长度在1-50")
    private String name;

    @NotNull(message = "categoryId不能为空")
    private Long categoryId;


    @NotNull(message = "销售模式不能为空")
    private int mode;  //1为普通，2为预付


    @NotNull(message = "库存模式不能为空")
    private int stockType; //1为拍下减少库存，2为付款减库存

    @NotNull(message = "商品状态不能为空")
    private int statusType; //商品状态，1为上架中，2为下架中


    private String description; //商品描述

    @NotNull(message = "封面图不能为空")
    private String coverUrl; //封面图片

    @JsonSerialize(using = Decimal2Serializer.class)
    private BigDecimal price;//展示价格
    @JsonSerialize(using = Decimal2Serializer.class)
    private BigDecimal originalPrice; //商品原价
    @JsonSerialize(using = Decimal2Serializer.class)
    private BigDecimal expressPrice;//快递费

    private Long saleNum;//销量

    private Long lastNum; //剩余库存

    private int deleteFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private List<ProductDetailAttribute> attributes;

    private List<ProductDetailImg> detailImages;

    private List<ProductDetailMainImg> mainImages;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getStockType() {
        return stockType;
    }

    public void setStockType(int stockType) {
        this.stockType = stockType;
    }

    public int getStatusType() {
        return statusType;
    }

    public void setStatusType(int statusType) {
        this.statusType = statusType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<ProductDetailAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductDetailAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<ProductDetailImg> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(List<ProductDetailImg> detailImages) {
        this.detailImages = detailImages;
    }

    public List<ProductDetailMainImg> getMainImages() {
        return mainImages;
    }

    public void setMainImages(List<ProductDetailMainImg> mainImages) {
        this.mainImages = mainImages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Long saleNum) {
        this.saleNum = saleNum;
    }

    public Long getLastNum() {
        return lastNum;
    }

    public void setLastNum(Long lastNum) {
        this.lastNum = lastNum;
    }

    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }
}
