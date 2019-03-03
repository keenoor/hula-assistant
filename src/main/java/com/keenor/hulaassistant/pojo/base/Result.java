package com.keenor.hulaassistant.pojo.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.keenor.hulaassistant.config.BizException;
import com.keenor.hulaassistant.config.ResponseCode;

import java.io.Serializable;

/**
 * Author:      chenliuchun
 * Date:        2018/6/5
 * Description: 通用接口返回参数类
 * Modification HistoryItem:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 * @author zccp
 */

//序列化json的时候,如果是null的对象, key也会消失
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    private String code;
    private String msg;
    private T detail;

    public Result(String code) {
        this.code = code;
    }

    private Result(String code, T detail) {
        this.code = code;
        this.detail = detail;
    }

    private Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(String code, String msg, T detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    @JsonIgnore //使之不在json序列化结果当中
    public boolean isSuccess() {
        return ResponseCode.SUCCESS.name().equals(this.code);
    }

    public String getCode() {
        return code;
    }

    public T getDetail() {
        return detail;
    }

    public String getMsg() {
        return msg;
    }


    public static <T> Result<T> ofSuccess() {
        return new Result<T>(ResponseCode.SUCCESS.name());
    }

    public static <T> Result<T> ofSuccessMsg(String msg) {
        return new Result<T>(ResponseCode.SUCCESS.name(), msg);
    }

    public static <T> Result<T> ofSuccess(T data) {
        return new Result<T>(ResponseCode.SUCCESS.name(), data);
    }

    public static <T> Result<T> ofSuccess(String msg, T data) {
        return new Result<T>(ResponseCode.SUCCESS.name(), msg, data);
    }

    public static <T> Result<T> ofError() {
        return new Result<T>(ResponseCode.ERROR.name(), ResponseCode.ERROR.getMessage());
    }

    public static <T> Result<T> ofErrorCode(String code) {
        return new Result<T>(code);
    }

    public static <T> Result<T> ofErrorMsg(String errorMessage) {
        return new Result<T>(ResponseCode.ERROR.name(), errorMessage);
    }

    public static <T> Result<T> ofErrorCodeMsg(String errorCode, String errorMessage) {
        return new Result<T>(errorCode, errorMessage);
    }

    public static <T> Result<T> ofErrorCodeMsg(ResponseCode errorCode, String errorMessage) {
        return new Result<T>(errorCode.name(), errorMessage);
    }

    public static <T> Result<T> ofErrorCodeMsg(ResponseCode errorCode) {
        return new Result<T>(errorCode.name(), errorCode.getMessage());
    }

    public static <T> Result<T> ofErrorCodeMsg(BizException e) {
        return new Result<T>(e.getCode(), e.getMessage());
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg=" + msg +
                ", detail=" + detail +
                '}';
    }

}




