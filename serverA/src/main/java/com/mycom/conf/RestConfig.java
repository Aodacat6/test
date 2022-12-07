package com.mycom.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：songdalin
 * @date ：2022/12/5 下午 4:55
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
