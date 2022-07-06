package com.test.controller;

import com.test.idempotencectrl.annotation.IdempotenceCtrl;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：songdalin
 * @date ：2022/7/6 下午 2:48
 * @description：
 * @modified By：
 * @version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @IdempotenceCtrl(intervalTime = 50000, markClass = Para.class)
    //@IdempotenceCtrl
    @GetMapping("/get")
    public String get(Para para, String xxd) {
        System.out.println(para.toString());
        System.out.println(para);
        return "success";
    }
}

@Data
class Para {
    private String name;
}