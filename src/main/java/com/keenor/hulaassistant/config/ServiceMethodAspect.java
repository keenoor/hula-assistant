package com.keenor.hulaassistant.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: chenliuchun
 * Date:        2018/8/16
 * Description: service 各个方法的参数打印
 * Modification HistoryItem:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */

@Aspect
@Order(4)
@Component
public class ServiceMethodAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceMethodAspect.class);

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void service() {}

    /**
     * 指定包内的 Service
     */
    @Pointcut("execution(public * com.zchz.safechainconsolebe.service.*Service.*(..))")
    public void credentialMethod() {}

    /**
     * 执行前
     * 记录 Service 方法入参和出参
     * @param joinPoint joinPoint
     */
    @Before("service() && credentialMethod()")
    public void logBefore(JoinPoint joinPoint) {

        // 记录类名及方法名
        logger.info("+++ SERVICE_CLASS_METHOD +++ : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());

        // 记录请求参数
        logger.info("+++ SERVICE_INPUT_ARGS --- :  " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 执行后
     * 请求结束, 记录返回内容
     * @param result 响应内容
     */
    @AfterReturning(pointcut = "service() && credentialMethod()", returning = "result")
    public void logAfterReturning(Object result) {
        logger.info("--- SERVICE_OUTPUT_ARGS --- : " + result);
    }

}

