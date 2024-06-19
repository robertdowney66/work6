package com.yuyu.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 将查询结果使用此类进行封装后返回
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /**
     * 用于返回查询状态的状态码
     */
    public static final Integer PROJECT_SUCCESS = 10000;
    public static final Integer SYSTEM_UNKNOW_ERROR = -3;
    public static final Integer PROJECT_BUSSINESS_ERROR = -4;
    public static final Integer AUTHENTICATION_ERROR = -1;
    public static final Integer ACCESSDENIED_ERROR = -2;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
