package com.keenor.hulaassistant.config;

/**
 * Author: chenliuchun
 * Date: 2018/6/19
 * Description:
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * Order(3) 制定 Aspect 处理顺序, 数值越小, 优先级越高
 */
@Aspect
@Order(3)
@Component
public class HttpLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>(); // 记录请求与响应花费的时间

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void controller() {}

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restController() {}

    @Pointcut("execution(public * com.keenor.hulaassistant.controller.*.*(..))")
    protected void allMethod() {}

    /**
     * 执行前
     * 记录 HTTP 请求详细
     * @param joinPoint joinPoint
     */
    @Before("allMethod() && (controller()||restController())")
    public void logBefore(JoinPoint joinPoint) {
        // 开始计时
        startTime.set(System.currentTimeMillis());

        logger.info("+++++ HTTP REQUEST START +++++");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 记录类名及方法名
        logger.info("HTTP_CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());

        // 记录请求方法
        logger.info("HTTP_METHOD : " + request.getMethod());
        // 记录请求参数
        logger.info("HTTP_ARGS :  " + Arrays.toString(joinPoint.getArgs()));
        // 记录请求地址
        logger.info("HTTP_REQUEST_URL : " + request.getRequestURL().toString());
        // 记录请求 IP
        logger.info("HTTP_REQUEST_IP : " + request.getRemoteAddr());
    }

    /**
     * 执行后
     * 请求结束, 记录返回内容
     * @param result 响应内容
     */
    @AfterReturning(pointcut = "allMethod() && (controller()||restController())", returning = "result")
    public void logAfterReturning(Object result) {
        logger.info("HTTP_RESPONSE : " + result);
        // 结束计时
        logger.info("HTTP_SPEND_TIME : " + (System.currentTimeMillis() - startTime.get()) + " ms");
        logger.info("----- HTTP REQUEST END -----");
    }

}