package com.mycom.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：songdalin
 * @date ：2022/6/13 下午 5:14
 * @description：
 * @modified By：
 * @version: 1.0
 */
@FeignClient(url = "http://localhost:8080", path = "/notest", value = "testservice")
public interface NoTest {


    @GetMapping("/noget")
    String notest();
}
