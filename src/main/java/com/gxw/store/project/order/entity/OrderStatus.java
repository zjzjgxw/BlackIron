package com.gxw.store.project.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gxw.store.project.common.utils.enumHandler.BaseEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatus implements BaseEnum {
    INVALID(0, "无效"),
    UNPAID(1, "待支付"),
    WAIT_SEND(2, "待发货"),
    HAS_SEND(3,"已发货"),
    WAIT_COMMENT(4,"待评价"),
    FINISHED(5,"已完成"),
    APPLY_REFUND(6,"申请退款"),
    AGREE_REFUND(7,"同意退款"),
    REFUSE_REFUND(8,"拒绝退款");

    private Integer index;
    private String text;

    OrderStatus(Integer i, String s) {
        index = i;
        text = s;
    }


    @Override
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
