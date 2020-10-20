package com.gxw.store.project.user.entity.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gxw.store.project.common.utils.enumHandler.BaseEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BusinessStatus implements BaseEnum {
    WAIT_VERIFY(0, "待审核"),
    SUCCESS_VERIFY(1, "审核成功"),
    FAILED_VERIFY(2, "审核失败");

    private Integer index;
    private String text;

    BusinessStatus(Integer i, String s) {
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
