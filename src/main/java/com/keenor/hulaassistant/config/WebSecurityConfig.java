package com.keenor.hulaassistant.config;

import com.keenoor.toolkit.utils.GsonUtil;
import com.keenor.hulaassistant.pojo.base.Result;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-10 15:15
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "SESSION_KEY";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
//        addInterceptor.excludePathPatterns("/user/login", "/user/signup");
        // 拦截配置
//        addInterceptor.addPathPatterns("/**");

        addInterceptor.excludePathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) == null) {
                // 返回未登录错误
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                Result<Void> result = Result.ofErrorCodeMsg(ResponseCode.NOT_LOGIN);
                response.getWriter().write(GsonUtil.toJson(result));
                return false;
            }
            return true;
        }
    }
}
