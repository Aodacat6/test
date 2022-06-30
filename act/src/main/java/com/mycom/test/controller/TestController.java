package com.mycom.test.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.mycom.redislock.service.RedisLockUtil;
import com.mycom.test.client.vo.StudentVo;
import com.mycom.test.entity.MyHolidayRecord;
import com.mycom.test.mapper.MyHolidayRecordMapper;
import com.mycom.test.service.TestService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：songdalin
 * @date ：2022/6/10 下午 6:22
 * @description：
 * @modified By：
 * @version: 1.0
 */
@RestController
@RequestMapping("/say")
public class TestController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private MyHolidayRecordMapper holidayRecordMapper;

    @Autowired
    private RedisLockUtil redisLockUtil;

    @Autowired
    private TestService testService;

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/get")
    public String getRe() {
        Wrapper<MyHolidayRecord> wrapper = new EntityWrapper<>();
        wrapper.eq("name", "11");
        final List<Object> objects = holidayRecordMapper.selectObjs(wrapper);
        if (CollectionUtil.isEmpty(objects)) {
            return "";
        }
        return "yes";
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/add")
    public void setAndWait() throws InterruptedException {
        MyHolidayRecord record = new MyHolidayRecord();
        record.setName("11");
        record.setProcessId("222");
        holidayRecordMapper.insert(record);
        Thread.sleep(10000);
        System.out.println("over=============");
    }


    @GetMapping("/hello-ser")
    public String helloSer () {
        return testService.hhhh();
    }

    @GetMapping("/hello")
    public String hello () {
        return "hello";
    }

    @GetMapping("/student")
    public StudentVo get(StudentVo vo, String type) {
        return vo;
    }

    @GetMapping("/lock")
    public String lock () {
        redisLockUtil.lock("111");
        return "success";
    }

    /**
     * 小王发起请假流程
     *      即开始新流程
     * @return
     */
    @GetMapping("/start")
    public String startHoliday() {
        MyHolidayRecord record = new MyHolidayRecord();
        record.setName("小明");
        final Integer insert = holidayRecordMapper.insert(record);
        System.out.println(record.getId());
        //final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday");
        return "success";
    }


}
