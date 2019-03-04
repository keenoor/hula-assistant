package com.keenor.hulaassistant.pojo.base;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.keenor.hulaassistant.config.ResponseCode;

import java.io.Serializable;

/**
 * @author:      chenliuchun
 * Date:        2018/8/15
 * Description: 老的通用接口返回参数类
 * Modification HistoryItem:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */

//序列化json的时候,如果是null的对象, key也会消失
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OldResult<T> implements Serializable {

    private Integer code;
    private T data;

    public OldResult(Integer code) {
        this.code = code;
    }

    private OldResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    @JsonIgnore //使之不在json序列化结果当中
    public boolean isSuccess() {
        return 200 == this.code;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }


    public static <T> OldResult<T> ofSuccess() {
        return new OldResult<T>(200);
    }

    public static <T> OldResult<T> ofSuccess(T detail) {
        return new OldResult<T>(200, detail);
    }

    public static <T> OldResult<T> ofError() {
        return new OldResult<T>(500);
    }

    public static <T> OldResult<T> ofErrorMsg(T errorMessage) {
        return new OldResult<T>(500, errorMessage);
    }

    public static <T> OldResult<T> ofErrorCodeMsg(Integer errorCode, T errorMessage) {
        return new OldResult<T>(errorCode, errorMessage);
    }

    @Override
    public String toString() {
        return "OldResult{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}




