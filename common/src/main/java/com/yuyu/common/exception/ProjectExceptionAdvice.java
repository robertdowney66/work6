package com.yuyu.common.exception;


import com.yuyu.common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
@Slf4j
/**
 * 定义全局异常类，用于异常的捕获与消息的记录
 */
public class ProjectExceptionAdvice {

    @ExceptionHandler(BussinessException.class)
    public Result doBusinessException(BussinessException exception){
        log.error(exception.getMessage());
        return new Result(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException exception){
        return new Result(exception.getCode(), exception.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public Result doException(Exception exception){
        // 记录日志
        log.error(exception.getMessage());
        return new Result(Result.SYSTEM_UNKNOW_ERROR,"系统繁忙，请联系管理员！");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result doException(UnauthorizedException exception){
        // 记录日志
        log.error(exception.getMessage());
        return new Result(Result.AUTHENTICATION_ERROR,exception.getMessage());
    }



}
