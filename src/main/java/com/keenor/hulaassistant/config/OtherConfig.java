package com.keenor.hulaassistant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 其他配置项，包含不需要体现在 yml 文件中的配置项
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2018-12-12 13:53
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

@Configuration
public class OtherConfig {

    @Autowired
    public void setWebMvcProperties(WebMvcProperties webMvcProperties){
        // URI 后缀加 .json 等不影响访问
        webMvcProperties.getPathmatch().setUseSuffixPattern(true);
    }

}
