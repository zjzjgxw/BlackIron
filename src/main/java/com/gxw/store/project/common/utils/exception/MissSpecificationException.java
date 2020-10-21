package com.gxw.store.project.common.utils.exception;

public class MissSpecificationException extends RuntimeException {
    private String defaultMessage;

    public MissSpecificationException() {
        this.defaultMessage = "缺少规格信息";
    }


    public MissSpecificationException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
