package com.gxw.store.project.order.entity;

import java.util.Date;

/**
 * 订单项
 */
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long productId;
    private String name; //商品名称
    private String coverUrl; //商品封面图
    private Long specificationId; //规格id
    private String firstSpecificationName; //第一个规格名
    private String firstSpecificationValue; //第一个规格值
    private String secondSpecificationName; //第二个规格名
    private String secondSpecificationValue; //第二个规格值
    private String productSnap; //商品描述信息快照。
    private Long price; //实际价格
    private Long originalPrice; //商品原价
    private int num; //数量
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    public String getFirstSpecificationName() {
        return firstSpecificationName;
    }

    public void setFirstSpecificationName(String firstSpecificationName) {
        this.firstSpecificationName = firstSpecificationName;
    }

    public String getFirstSpecificationValue() {
        return firstSpecificationValue;
    }

    public void setFirstSpecificationValue(String firstSpecificationValue) {
        this.firstSpecificationValue = firstSpecificationValue;
    }

    public String getSecondSpecificationName() {
        return secondSpecificationName;
    }

    public void setSecondSpecificationName(String secondSpecificationName) {
        this.secondSpecificationName = secondSpecificationName;
    }

    public String getSecondSpecificationValue() {
        return secondSpecificationValue;
    }

    public void setSecondSpecificationValue(String secondSpecificationValue) {
        this.secondSpecificationValue = secondSpecificationValue;
    }

    public String getProductSnap() {
        return productSnap;
    }

    public void setProductSnap(String productSnap) {
        this.productSnap = productSnap;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
