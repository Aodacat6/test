package com.mycom.test.service.impl;

import com.mycom.test.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author ：songdalin
 * @date ：2022/6/23 上午 9:52
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Service
public class TestServiceImpl implements TestService {


    @Override
    public String hhhh() {
        return "success";
    }
}
