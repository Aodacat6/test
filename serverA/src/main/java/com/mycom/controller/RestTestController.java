package com.mycom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：songdalin
 * @date ：2022/12/2 下午 3:46
 * @description：
 * @modified By：
 * @version: 1.0
 */
@RestController
@RequestMapping("/ServiceA")
public class RestTestController {

    @LoadBalanced
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String loadBalanceTest() {
        final String str = restTemplate.getForObject("http://serverB/ServiceB/test", String.class);
        System.out.println("rtn:  " + str);
        return str;
    }

}
