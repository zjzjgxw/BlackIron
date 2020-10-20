package com.gxw.store.project.common.utils.exception;

public class UnUsableUserException extends RuntimeException {
    private String defaultMessage;

    public UnUsableUserException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
