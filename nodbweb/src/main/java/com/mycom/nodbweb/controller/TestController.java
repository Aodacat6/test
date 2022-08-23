package com.mycom.nodbweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mycom.nodbweb.client.TestClient;

import java.math.BigDecimal;

/**
 * @author ：songdalin
 * @date ：2022/6/10 下午 6:22
 * @description：
 * @modified By：
 * @version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestClient testClient;

    @GetMapping("/get")
    public String test () {
        return "hello";
    }

    @GetMapping("/feign")
    public String testFeign() {
        return testClient.test();
    }

    @GetMapping("/test1")
    public String test1(String param) {
        StopWatch stopWatch = new StopWatch("method:");
        stopWatch.start();
        stopWatch.stop();
        System.out.println(stopWatch.getId() + " " + stopWatch.getTotalTimeSeconds());
        return "ss";
    }



}
