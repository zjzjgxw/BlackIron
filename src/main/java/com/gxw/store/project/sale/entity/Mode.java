package com.gxw.store.project.sale.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.gxw.store.project.common.utils.enumHandler.BaseEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Mode implements BaseEnum {
    ALL(0, "全场"),
    PRODUCT(1, "指定商品");

    @JsonValue
    private Integer index;
    private String text;

    Mode(Integer i, String s) {
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
