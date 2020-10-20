package com.gxw.store.project.user.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.gxw.store.project.common.utils.enumHandler.BaseEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AdminStatus implements BaseEnum {
    USABLE(0, "可用"),
    UNUSABLE(1, "禁用");

    @JsonValue
    private Integer index;
    private String text;

    AdminStatus(Integer i, String s) {
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
