package com.bhvote.auth.exception;


import domain.R;
import enums.AppHttpCodeEnum;
import exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 配置统一异常接口
 */
@RestControllerAdvice(basePackages = "com.bhvote.auth.controller")
@Slf4j
public class GlobalAuthExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e){
        //打印异常信息
        log.error("出现了异常 {}",e.getMessage());
        //从异常对象中获取提示信息封装返回
        return R.error().put("msg",e.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public R systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现了异常！ {}",e.getMessage());
        //从异常对象中获取提示信息封装返回
        return R.error(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(BindException.class)
    public R BindExceptionHandler(BindException e){
        //打印异常信息
        log.error("出现了异常！ {}",e.getBindingResult().getFieldError().getDefaultMessage());
        //从异常对象中获取提示信息封装返回
        return R.error(AppHttpCodeEnum.VALID_FAIL.getCode(),e.getBindingResult().getFieldError().getDefaultMessage());
    }

}
