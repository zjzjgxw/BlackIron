package com.gxw.store.project.order.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderSendParam {

    @NotNull
    private Long orderId;
    @NotNull
    private Long expressId;
    @NotBlank(message = "快递单号不能为空")
    private String expressCode;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
}
