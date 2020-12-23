package com.gxw.store.project.common.utils.exception;

public class ErrorPrepayIdException extends RuntimeException {
    private String defaultMessage;

    public ErrorPrepayIdException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
