package com.mycom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：songdalin
 * @date ：2022/12/2 上午 11:09
 * @description：
 * @modified By：
 * @version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServerCApplication {
    public static void main(String[] args){
        SpringApplication.run(ServerCApplication.class, args);
    }
}
