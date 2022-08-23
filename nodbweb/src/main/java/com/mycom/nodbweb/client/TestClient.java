package com.mycom.nodbweb.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：songdalin
 * @date ：2022/6/10 下午 5:22
 * @description：
 * @modified By：
 * @version: 1.0
 */
@FeignClient(url = "http://localhost:8080", path = "/test", value = "testservice")
public interface TestClient {

    @GetMapping("/get")
    String test();

}
