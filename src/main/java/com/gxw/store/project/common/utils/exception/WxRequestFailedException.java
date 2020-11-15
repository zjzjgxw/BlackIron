package com.gxw.store.project.common.utils.exception;

public class WxRequestFailedException extends RuntimeException {
    private String defaultMessage;

    public WxRequestFailedException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
