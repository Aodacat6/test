package com.mycom.test.handler;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.springframework.stereotype.Component;

/**
 * @author ：songdalin
 * @date ：2022/7/7 下午 3:34
 * @description：任务被完成了。它会在ENTITY_DELETE事件之前触发。当任务是流程一部分时，事件会在流程继续运行之前，   后续事件将是ACTIVITY_COMPLETE，对应着完成任务的节点
 * @modified By：
 * @version: 1.0
 */
@Component
public class TaskCompletedEventHandler implements EventHandler{
    @Override
    public void handler(ActivitiEvent event) {
        System.out.println("======监听任务完成事件======");

    }
}
