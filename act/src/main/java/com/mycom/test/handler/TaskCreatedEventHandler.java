package com.mycom.test.handler;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.springframework.stereotype.Component;

/**
 * @author ：songdalin
 * @date ：2022/7/7 下午 3:33
 * @description： * 创建了新任务。它位于ENTITY_CREATE事件之后。当任务是由流程创建时，     这个事件会在TaskListener执行之前被执行。
 * @modified By：
 * @version: 1.0
 */
@Component
public class TaskCreatedEventHandler implements EventHandler{
    @Override
    public void handler(ActivitiEvent event) {
        System.out.println("======监听任务创建事件======");

    }
}
