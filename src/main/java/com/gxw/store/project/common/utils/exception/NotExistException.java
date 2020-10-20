package com.gxw.store.project.common.utils.exception;

public class NotExistException extends RuntimeException {
    private String defaultMessage;

    public NotExistException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
