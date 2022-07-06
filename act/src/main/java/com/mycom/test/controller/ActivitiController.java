package com.mycom.test.controller;

import com.mycom.test.entity.MyHolidayRecord;
import com.mycom.test.mapper.MyHolidayRecordMapper;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：songdalin
 * @date ：2022/7/1 下午 3:10
 * @description：  流程引擎测试
 * @modified By：
 * @version: 1.0
 */
@RestController
@RequestMapping("/acti")
public class ActivitiController {

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
        //业务主键id赋值给流程的businessid
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday", record.getId().toString());
        if (processInstance == null) {
            return "fail";
        }
        //流程的id回写到业务表
        record.setProcessId(processInstance.getProcessInstanceId());
        holidayRecordMapper.updateById(record);
        return "success";
    }

    /**
     * 获取流程信息图，用于前端展示，任务实时状态
     * @param businessKey
     * @return
     */
    @GetMapping("/getProcessInfo/{businessKey}")
    public String getProcessInfo(@PathVariable("businessKey") String businessKey) {
        //repositoryService.getProcessDefinition();
        return "su";
    }

}


