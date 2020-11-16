package com.gxw.store.project.common.utils.exception;

/**
 * 文件名大小限制异常类
 * 
 * @author ruoyi
 */
public class FileSizeLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException()
    {
        super("文件过大");
    }
}
