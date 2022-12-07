package com.mycom.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.mycom.mapper.BankInfoMapper;
import com.mycom.mapper.OrderInfoMapper;
import com.mycom.pojo.BankInfo;
import com.mycom.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private TestComp testComp;

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String test() {

        return port;
    }

    @GetMapping("/testTas")
    public String testTas() {
        final int count  = orderInfoMapper.selectCount(null);
        System.out.println(count);
        return "suc";
    }

    /**
     *
     *      A 调用 B，并对B try catch，如果B中报错，且B中没有catch，那么A的切面会对事务设置为 rollback
     *      解决：
     *          1、A不要catch，有错误往上抛
     *          2、将 B的事务设置为 NESTED：支持当前事务，如果当前事务存在，则执行一个嵌套事务，如果当前没有事务，就新建一个事务
     *          3、
     * Transaction rolled back because it has been marked as rollback-only
     *
     *
     * @return
     */
    @Transactional
    @GetMapping("/test11")
    public String test11() {

        final long l = System.currentTimeMillis();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("wwwwww" + l);
        orderInfo.setPoNo("111");
        final Integer insert = orderInfoMapper.insert(orderInfo);
        try {
            final Integer testcom = testComp.testcom();
            System.out.println(testcom);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "su";
    }

    /**
     * 更新 - 锁
     * @return
     */
    @Transactional
    @GetMapping("/test22")
    public String test22() throws InterruptedException {
/*        new Thread(() -> {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setPoId(111L + 1);
            Condition condition = new Condition();
            condition.eq("po_no", "UKA210819004SREF");
            orderInfoMapper.update(orderInfo, condition);
            try {
                Thread.sleep(2 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
        new Thread(() -> {
            dos();
        }).start();

        Thread.sleep(100);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPoId(111L + 1);
        orderInfo.setPoDate("22");
        Condition condition = new Condition();
        condition.eq("po_no", "UKA210819004SREF");
        orderInfoMapper.update(orderInfo, condition);


        Thread.sleep(3 * 60 * 1000);
        return "ss";
    }

    /**
     * mysql 默认是自动提交，即执行一条提交一条
     * @Transactional 注解开始前，会将自动提交关闭
     * con.setAutoCommit(false);
     *
     * 报错： Lock wait timeout exceeded; try restarting transaction
     */
    @Transactional
    public void dos () {
        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo1.setPoId(111L + 2);
        Condition condition1 = new Condition();
        condition1.eq("po_no", "UKA210819004SREF");
        orderInfoMapper.update(orderInfo1, condition1);
        try {
            Thread.sleep(2 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==========do is done============");
    }

}
