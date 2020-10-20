package com.gxw.store.project.common.utils.exception;

public class HasExistException extends RuntimeException {
    private String defaultMessage;

    public HasExistException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
