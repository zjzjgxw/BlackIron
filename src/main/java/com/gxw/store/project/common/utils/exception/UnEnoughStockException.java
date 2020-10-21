package com.gxw.store.project.common.utils.exception;

public class UnEnoughStockException extends RuntimeException {
    private String defaultMessage;

    public UnEnoughStockException() {
        this.defaultMessage = "库存不足";
    }


    public UnEnoughStockException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
