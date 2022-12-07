package com.mycom.controller;

import com.mycom.mapper.OrderInfoMapper;
import com.mycom.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：songdalin
 * @date ：2022/12/5 上午 9:43
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Component
public class TestComp {

    @Autowired
    private OrderInfoMapper orderInfoMapper;


    @Transactional
    public Integer testcom() {
        final long l = System.currentTimeMillis();
/*        try {
            int i = 9 / 0;
        } catch (Exception e) {
            System.out.println("error");
        }*/

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("wwwwww" + l);
        orderInfo.setPoNo("111");
        int insert = 1;
        insert = orderInfoMapper.insert(orderInfo);
        //int i = 9 / 0;
        return insert;
    }
}
