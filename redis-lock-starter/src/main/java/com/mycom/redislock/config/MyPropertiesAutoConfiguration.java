package com.mycom.redislock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：songdalin
 * @date ：2022/6/20 上午 9:43
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Configuration
public class MyPropertiesAutoConfiguration {

    @Bean
    public MyProperties myProperties() {
        return new MyProperties();
    }
}
