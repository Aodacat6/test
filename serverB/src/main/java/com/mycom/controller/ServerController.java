package com.mycom.controller;

//import com.mycom.mapper.BankInfoMapper;
import com.mycom.pojo.BankInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：songdalin
 * @date ：2022/12/2 下午 3:42
 * @description：
 * @modified By：
 * @version: 1.0
 */
@RestController
@RequestMapping("/ServiceB")
public class ServerController {

/*    @Autowired
    private BankInfoMapper bankInfoMapper;*/

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String test() {

        return port;
    }

/*    @GetMapping("/testTas")
    public String testTas() {
        final List<BankInfo> bankInfos = bankInfoMapper.selectList(null);
        System.out.println(bankInfos);
        return "suc";
    }*/
}
