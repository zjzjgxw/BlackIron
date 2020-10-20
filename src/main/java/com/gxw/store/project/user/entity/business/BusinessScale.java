package com.gxw.store.project.user.entity.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gxw.store.project.common.utils.enumHandler.BaseEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BusinessScale implements BaseEnum {
    SMALL(0, "0-50人"),
    MID(1, "50-500人"),
    BIG(2, "500以上");

    private Integer index;
    private String text;

    BusinessScale(Integer i, String s) {
        index = i;
        text = s;
    }


    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
