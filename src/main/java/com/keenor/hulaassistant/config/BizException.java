package com.keenor.hulaassistant.config;

/**
 * @author: chenliuchun
 * Date:        2018/8/15
 * Description: 业务相关运行时异常
 * Modification HistoryItem:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 9164117137727846171L;

    private String code;

    private String text;

    public BizException(String code) {
        this.code = code;
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
        this.text = message;
    }

    public BizException(String code, StackTraceElement[] stackTraceElements) {
        this.code = code;
        setStackTrace(stackTraceElements);
    }

    public BizException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BizException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.name();
        this.text = responseCode.getMessage();
    }

    public BizException(ResponseCode responseCode, String message) {
        super(message);
        this.code = responseCode.name();
        this.text = message;
    }

    public BizException(ResponseCode responseCode, Throwable cause) {
        super(cause);
        this.code = responseCode.name();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}