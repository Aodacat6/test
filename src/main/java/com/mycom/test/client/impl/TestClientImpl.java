package com.mycom.test.client.impl;

import com.mycom.test.client.NoTest;
import com.mycom.test.client.TestClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：songdalin
 * @date ：2022/6/10 下午 5:23
 * @description：
 * @modified By：
 * @version: 1.0
 */
@RestController
@RequestMapping("/notest")
public class TestClientImpl implements NoTest {


    @Override
    public String notest() {
        return "notest successs";
    }
}
