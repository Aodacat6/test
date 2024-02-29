package com.mycom.nodbweb.controller;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mycom.nodbweb.client.TestClient;
import org.springframework.web.context.request.async.DeferredResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ：songdalin
 * @date ：2022/6/10 下午 6:22
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Tag(name = "模板管理")
@DependsOn("compon2")
//@ConditionalOnBean(name = "compon2")
//@Import(Compon2.class)
@Slf4j
//@EnableScheduling
@RestController
@RequestMapping("/test")
public class TestController2 implements BeanFactoryAware {

   // @Autowired
    //private Compon2 compon2;

    private static final Multimap<String, DeferredResult> map = ArrayListMultimap.create();

    @Autowired
    private TestClient testClient;

    private LinkedBlockingQueue<List<User>> queue = new LinkedBlockingQueue<>();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("j进入aware======");
        //compon2 = (Compon2) beanFactory.getBean("compon2");
    }


/*    @GetMapping("/deferred/{id}")
    public DeferredResult<String> test2(@PathVariable("id") String id) {
        System.out.println("start===============" + Thread.currentThread().getName());
        DeferredResult result = new DeferredResult(15000L);
        queue.offer(id);
        System.out.println("over===============" + Thread.currentThread().getName());
        //result.setResult("ddd");
        return result;
    }*/

    @Data
    class User {
        private String id;
        private String name;
    }

/*    @GetMapping("/async/{id}")
    public User test2(@PathVariable("id") String id) {
        System.out.println("start===============" + Thread.currentThread().getName());

        User user = new User();
        user.setId(id);
        queue.offer(user);
        System.out.println("over===============" + Thread.currentThread().getName());
        //result.setResult("ddd");
        try {
            return queue.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("xxxx is exception  xxxx");
    }

    public*/

/*    private String dosome() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "suss";
        }).whenCompleteAsync((x, y) -> {
            System.out.println("finish===============" + Thread.currentThread().getName());
            result.setResult(x);
        });
    }*/

/*    @GetMapping("/get")
    public String test () {
        compon2.dos();
        return "hello";
    }*/

    @Operation(summary = "获取模板json数据")
    @GetMapping("/feign")
    public String testFeign() {
        return testClient.test();
    }

    @GetMapping("/test1")
    public String test1(String param) {
        StopWatch stopWatch = new StopWatch("method:");
        stopWatch.start();
        stopWatch.stop();
        System.out.println(stopWatch.getId() + " " + stopWatch.getTotalTimeSeconds());
        return "ss";
    }

    @GetMapping("/async")
    public DeferredResult<String> asyncGet(String name) {
        log.info("========进入controller");
        DeferredResult<String> result = new DeferredResult<>();
        map.put(name, result);
        result.onCompletion(() -> {
            log.info("===========方法完成");
            map.remove(name, result);
        });
        log.info("==============准备返回");
        //return result;
        return result;
    }

    //@Scheduled(fixedDelay = 5000)
    public void dosome() {
        for (Map.Entry<String, DeferredResult> entry : map.entries()) {
            DeferredResult value = entry.getValue();;
            value.setResult(entry.getKey());
        }
    }


}
