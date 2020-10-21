package com.gxw.store.project.common.utils.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gxw.store.project.common.utils.enumHandler.BaseEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExpressType implements BaseEnum {
    UN_KNOWN(0, "未知"),
    SHU_FENG(1, "顺丰"),
    YUAN_TONG(2, "圆通"),
    SHEN_TONG(3, "申通");

    private Integer index;
    private String text;

    ExpressType(Integer i, String s) {
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
