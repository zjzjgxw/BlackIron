package com.gxw.store.project.common.utils.exception;

public class InvalidUserException extends RuntimeException {
    private String defaultMessage;

    public InvalidUserException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
