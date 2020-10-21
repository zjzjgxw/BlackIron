package com.gxw.store.project.common.utils.exception;

public class ErrorTokenException extends RuntimeException {
    private String defaultMessage;

    public ErrorTokenException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
