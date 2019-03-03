package com.keenor.hulaassistant.config;



import com.keenor.hulaassistant.pojo.base.Result;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.net.SocketTimeoutException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: chenliuchun
 * @date: 2018/9/19
 * Description: 全局异常捕获类
 * Modifcation HistoryItem:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Result handlerException(BizException e) {
        log.error("BizException--> code: {}, text: {}", e.getCode(), e.getText());
        log.error("", e);
        return Result.ofErrorCodeMsg(e.getCode(), e.getText());
    }

    /**
     * get 请求参数缺失
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handlerException(MissingServletRequestParameterException e) {
        log.error("请求参数缺失--> params:{}, message:{}", e.getParameterName(), e.getMessage());
        return Result.ofErrorCodeMsg(ResponseCode.PARAM_MISSING_ERROR, e.getMessage());
    }

    /**
     * post 请求参数校验失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handlerException(MethodArgumentNotValidException e) {
        log.error("请求参数校验错误--> params:{}, message:{}", e.getBindingResult().getObjectName(), e.getMessage());
        return Result.ofErrorCodeMsg(ResponseCode.PARAM_VERIFY_ERROR, e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public Result handlerException(MultipartException e) {
        log.error("上传文件大小超限--> message:{}", e.getMessage());
        return Result.ofErrorCodeMsg(ResponseCode.PARAM_VERIFY_ERROR, e.getMessage());
    }

    /**
     * POST 请求方式为 json 时候，反序列化时候会报出此错误
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handlerException(HttpMessageNotReadableException e) {
        log.error("HttpServletRequest 请求体读取异常--> message:{}", e.getMessage());
        return Result.ofErrorCodeMsg(ResponseCode.PARAM_VERIFY_ERROR, e.getMessage());
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public Result handlerException(SocketTimeoutException e) {
        log.error("HTTP CLIENT 连接超时--> message:{}", e.getMessage());
        return Result.ofErrorCodeMsg(ResponseCode.HTTP_TIMEOUT_EXCEPTION, e.getMessage());
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        log.error("Unknown Exception--> ", e);
        return Result.ofErrorCodeMsg(ResponseCode.SYSTEM_ERROR.name(), ResponseCode.SYSTEM_ERROR.getMessage());
    }

}
