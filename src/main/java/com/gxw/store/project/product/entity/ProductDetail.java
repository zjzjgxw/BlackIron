package com.gxw.store.project.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 商品详情
 */
public class ProductDetail {

    private Long id;
    @NotNull(message = "bussinessId不能为空")
    private Long businessId;

    @NotBlank(message = "商品名不能为空")
    @Length(min = 1, max = 25, message = "商品名长度在1-5")
    private String name;

    @NotNull(message = "categoryId不能为空")
    private Long categoryId;


    @NotNull(message = "销售模式不能为空")
    private int mode;  //1为普通，2为预付


    @NotNull(message = "库存模式不能为空")
    private int stockType; //1为拍下减少库存，2为付款减库存

    @NotNull(message = "商品状态不能为空")
    private int statusType; //商品状态，1为上架中，2为下架中

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
}