package com.gxw.store.project.common.utils.exception;

public class ErrorParamException extends RuntimeException {
    private String defaultMessage;

    public ErrorParamException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
