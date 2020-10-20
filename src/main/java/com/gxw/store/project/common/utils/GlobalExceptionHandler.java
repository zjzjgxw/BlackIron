package com.gxw.store.project.common.utils;


import com.gxw.store.project.common.utils.constants.HttpStatus;
import com.gxw.store.project.common.utils.exception.HasExistException;
import com.gxw.store.project.common.utils.exception.InvalidUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e)
    {
        log.info(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseResult.error(message);
    }

    @ExceptionHandler(HasExistException.class)
    public Object handleHasExistException(HasExistException e)
    {
        log.info(e.getMessage(), e);
        String message = e.getMessage();
        return ResponseResult.error(HttpStatus.CONFLICT, message);
    }

    @ExceptionHandler(InvalidUserException.class)
    public Object handleInvalidUserException(InvalidUserException e)
    {
        log.info(e.getMessage(), e);
        String message = e.getMessage();
        return ResponseResult.error(HttpStatus.NOT_FOUND, message);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Object handleDuplicateKeyException(DuplicateKeyException e){
        log.info(e.getMessage(), e);
        return ResponseResult.error(HttpStatus.CONFLICT, "唯一键冲突"+e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        log.info(e.getMessage(), e);
        return ResponseResult.error(HttpStatus.ERROR, "参数错误");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        log.info(e.getMessage(), e);
        return ResponseResult.error(HttpStatus.ERROR, "参数类型错误");
    }
}
