package com.gxw.store.project.sale.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.gxw.store.project.common.utils.enumHandler.BaseEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CouponUseStatus implements BaseEnum {
    UN_USED(0, "未使用"),
    USED(1, "已使用"),
    UN_START(2,"未开始"),
    EXPIRED(3,"已过期"),
    UN_FIT(4,"不可用");

    private Integer index;
    private String text;

    CouponUseStatus(Integer i, String s) {
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
