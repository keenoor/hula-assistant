package com.keenor.hulaassistant.config;

import lombok.Getter;

/**
 * Created by chensc on 2018/3/6.
 */

@Getter
public enum ResponseCode {

    SUCCESS("成功"),
    ERROR("未知错误"),
    SYSTEM_ERROR("系统内部错误"),

    PARAM_VERIFY_ERROR("参数数据校验失败"),
    SIGN_VERIFY_ERROR("签名数据校验失败"),
    PARAM_MISSING_ERROR("参数缺失错误"),
    VERIFY_PHONE_ERROR("手机号不合法"),

    NOT_LOGIN("未登录"),
    PASSWORD_ERROR("密码错误"),
    LOGIN_TIMEOUT("用户登录超时"),
    USER_NOT_EXIST("用户不存在"),
    REPEAT_SIGNUP("重复注册"),
    ONLY_SIGNUP_ONCE("只允许注册一次"),

    /************** 合约相关 *************/
    ZERO_ADDRESS_IS_NOT_EXIST("合约不存在"),

    /************** db *************/
    DB_QUERY_NOT_EXIST("数据库查询无此数据"),
    DB_QUERY_IS_NOT_UNIQUE("数据库查询不唯一"),
    CACHE_QUERY_NOT_EXIST("缓存查询不存在"),

    /************** http request *************/
    HTTP_STATUS_EXCEPTION("http 状态码异常"),
    HTTP_TIMEOUT_EXCEPTION("http 连接超时"),
    HTTP_CLIENT_EXCEPTION("http client 异常"),
    API_STATUS_EXCEPTION("api 状态码异常"),

    DELETE_INVALID("非法删除"),

    /************** json *************/
    JSON_PARSE_ERROR("json 解析异常"),

    /************** 文件读写 *************/
    FILE_READ_ERROR("文件写入异常"),
    UPLOAD_FILE_SIZE_OVER("上传文件大小超限"),
    UPLOAD_FILE_FORMAT_ERROR("上传文件格式错误"),
    VERSION_FORMAT_ERROR("版本格式错误"),
    VERSION_LITTER_HISTORY("版本小于历史版本"),

    /************** sms *************/
    SMS_SEND_TOO_FREQUENTLY("短信发送频繁，间隔小于 1 分钟"),
    SMS_CONFIG_ERROR("短信发送配置异常"),
    SMS_SEND_ERROR("短信验证码发送异常")
    ;
    
    /**
     * 错误码解释
     */
    private String message;

    ResponseCode(String message) {
        this.message = message;
    }

}
