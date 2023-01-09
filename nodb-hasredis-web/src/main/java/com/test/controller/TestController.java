package com.test.controller;

import com.test.idempotencectrl.annotation.IdempotenceCtrl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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

    @Autowired
    private StringRedisTemplate redisTemplate;

    @IdempotenceCtrl(intervalTime = 50000, markClass = Para.class)
    //@IdempotenceCtrl
    @GetMapping("/gettest")
    public String get(Para para, String xxd) {
        System.out.println(para.toString());
        System.out.println(para);
        return "success";
    }

    @GetMapping("/testscript")
    public String testscript() {
        String scripts = "if 1 == redis.call('exists',KEYS[1]) then " +
                        "    if tonumber(redis.call('get',KEYS[1])) < 1 then " +
                        "        return -1 " +
                        "    else " +
                        "        return redis.call('decr',KEYS[1]) " +
                        "    end " +
                        "else " +
                        "    return redis.call('incr',KEYS[1]) " +
                        "end";
        RedisScript<Long> script = new DefaultRedisScript<>(scripts, Long.class) ;

        final Long flag = redisTemplate.execute(script, Arrays.asList("seqno"), "");
        System.out.println(flag);
        return "succ";
    }
}

@Data
class Para {
    private String name;
}