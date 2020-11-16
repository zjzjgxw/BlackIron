package com.gxw.store.project.common.utils.exception;

/**
 * 文件名大小限制异常类
 * 
 * @author ruoyi
 */
public class InvalidFileTypeException extends FileException
{
    private static final long serialVersionUID = 1L;

    public InvalidFileTypeException()
    {
        super("文件类型不符合");
    }
}
