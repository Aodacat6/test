package com.mycom.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：songdalin
 * @date ：2022/6/13 下午 5:01
 * @description：
 * @modified By：
 * @version: 1.0
 */
@RestController
@RequestMapping("/test")
public class FeignController {

    @GetMapping("/get")
    public String test() {
        return "test-success";
    }
}
