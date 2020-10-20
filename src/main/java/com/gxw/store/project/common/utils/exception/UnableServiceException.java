package com.gxw.store.project.common.utils.exception;

public class UnableServiceException extends RuntimeException {
    private String defaultMessage;

    public UnableServiceException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
