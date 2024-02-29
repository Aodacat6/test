package com.mycom.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.mycom.mapper.OrderInfoMapper;
import com.mycom.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：songdalin
 * @date ：2023/1/10 上午 11:28
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Service
public class DDDservice {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Transactional
    public void dos111 () {
        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo1.setPoId(System.currentTimeMillis());
        Condition condition1 = new Condition();
        //condition1.eq("po_no", "UKA210819004SREF");
        condition1.eq("order_id", "GSPI202110274178");
        orderInfoMapper.update(orderInfo1, condition1);
        System.out.println("==========do is done============");
    }
}
