package com.mycom.test.handler;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.springframework.stereotype.Component;

/**
 * @author ：songdalin
 * @date ：2022/7/7 下午 3:35
 * @description： 任务被分配给了一个人员。事件包含任务。
 * @modified By：
 * @version: 1.0
 */
@Component
public class TaskAssignedEventHandler implements EventHandler{
    @Override
    public void handler(ActivitiEvent event) {
        System.out.println("======监听任务分配人事件======");
    }
}
