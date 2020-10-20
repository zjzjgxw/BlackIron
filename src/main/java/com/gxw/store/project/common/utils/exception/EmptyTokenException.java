package com.gxw.store.project.common.utils.exception;

public class EmptyTokenException extends RuntimeException {
    private String defaultMessage;

    public EmptyTokenException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
