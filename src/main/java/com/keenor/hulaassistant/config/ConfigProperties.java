package com.keenor.hulaassistant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: chenliuchun
 * @date: 11/1/0001
 * Description: a
 * Modifcation HistoryItem:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "keenor")
@Component
public class ConfigProperties {


}

