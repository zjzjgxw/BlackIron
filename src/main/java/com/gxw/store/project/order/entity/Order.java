package com.gxw.store.project.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gxw.store.project.common.utils.enums.ExpressType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class Order {

    private Long id;

    private Long businessId;//商户id

    private String businessName; //商户名称

    private Long userId; //下单人id

    private String code; //生成的订单编号

    private Long price; //实付金额

    private Long originalPrice; //订单原总价格

    private Long expressPrice; //邮费

    private Long expressId; //快递类型

    private String expressName; //快递公司名称

    private String expressCode; //快递单号

    @NotBlank(message = "收件人不能为空")
    private String receiver; //收件人

    @NotBlank(message = "收件人联系电话不能为空")
    private String telphone; //联系电话

    private String province; //省
    private String city; //市
    private String county; //县

    @NotBlank(message = "收件地址不能为空")
    private String address; //收货地址

    private OrderStatus status;//订单状态

    private int deleteFlag; //删除标记

    private Date createTime; //订单创建时间

    private Date payTime; //订单支付时间

    private Date sendTime; //发货时间

    private Long couponId; //优惠券id

    private String remark; //备注

    private List<OrderItem> items;

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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Long getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(Long expressPrice) {
        this.expressPrice = expressPrice;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @JsonIgnore
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }


    public String getTitle(){
        StringBuilder title = new StringBuilder();
        for(OrderItem item: this.items){
            title.append(item.getName()).append("&");
        }
        if(title.length() >= 127){
            return title.substring(0,127);
        }else{
            return title.toString();
        }
    }
}
