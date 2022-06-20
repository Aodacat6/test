package com.mycom.redislock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author ：songdalin
 * @date ：2022/6/20 上午 9:40
 * @description：
 * @modified By：
 * @version: 1.0
 */
@EnableConfigurationProperties(MyProperties.class)
@ConfigurationProperties(prefix = "redis-lock")
public class MyProperties {
    private String name;
}
