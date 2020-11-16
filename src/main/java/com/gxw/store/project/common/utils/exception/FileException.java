package com.gxw.store.project.common.utils.exception;

/**
 * 文件信息异常类
 * 
 */
public class FileException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public FileException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }
}
