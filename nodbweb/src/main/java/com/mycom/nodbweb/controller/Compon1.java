package com.mycom.nodbweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：songdalin
 * @date ：2023/2/23 下午 6:01
 * @description：
 * @modified By：
 * @version: 1.0
 */
@ConditionalOnBean(name = "compon2")
@RestController
@RequestMapping("/com1")
@Component
public class Compon1 {

    @Autowired
    private Compon2 compon2;

    @GetMapping("/doss")
    public void doss() {
        compon2.syncdo();
        System.out.println("doss11: " + Thread.currentThread().getName());

    }
}
