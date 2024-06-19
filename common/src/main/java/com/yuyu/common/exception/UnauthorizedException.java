package com.yuyu.common.exception;

/**
 * 自定义异常类，用于业务层中出现的异常
 */
public class UnauthorizedException extends RuntimeException{
    private Integer code;

    public UnauthorizedException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public UnauthorizedException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
