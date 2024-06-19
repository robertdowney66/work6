package com.yuyu.common.exception;

/**
 * 自定义系统出现异常的异常类
 */
public class SystemException extends RuntimeException{
    private Integer code;

    public SystemException( Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SystemException( Integer code,String message) {
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
