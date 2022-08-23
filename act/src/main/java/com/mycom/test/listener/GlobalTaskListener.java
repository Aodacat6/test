package com.mycom.test.listener;

import com.mycom.test.handler.EventHandler;
import com.mycom.test.handler.TaskAssignedEventHandler;
import com.mycom.test.handler.TaskCompletedEventHandler;
import com.mycom.test.handler.TaskCreatedEventHandler;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：songdalin
 * @date ：2022/7/7 下午 2:39
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Component
public class GlobalTaskListener implements ActivitiEventListener {

    @Autowired
    private TaskAssignedEventHandler taskAssignedEventHandler;

    @Autowired
    private TaskCreatedEventHandler taskCreatedEventHandler;

    @Autowired
    private TaskCompletedEventHandler taskCompletedEventHandler;

    /**
     * 全局事件存储
     */
    public static Map<ActivitiEventType, EventHandler> eventHandlerMap = new HashMap<>();

    @PostConstruct
    public void init() {
        eventHandlerMap.put(ActivitiEventType.TASK_ASSIGNED, taskAssignedEventHandler);
        eventHandlerMap.put(ActivitiEventType.TASK_CREATED, taskCreatedEventHandler);
        eventHandlerMap.put(ActivitiEventType.TASK_COMPLETED, taskCompletedEventHandler);
    }

    @Override
    public void onEvent(ActivitiEvent event) {
        final EventHandler eventHandler = eventHandlerMap.get(event.getType());
        if (eventHandler == null) {
            System.out.println("没有配置事件：" + event.getType() + "的监听器");
            return;
        }
        eventHandler.handler(event);
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
