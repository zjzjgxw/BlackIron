package com.gxw.store.project.common.utils.enumHandler;

public interface BaseEnum<E extends Enum<?>, T> {
    public Integer getIndex();
    public String getText();
}
