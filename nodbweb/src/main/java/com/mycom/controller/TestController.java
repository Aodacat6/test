package com.mycom.controller;

import com.mycom.client.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
