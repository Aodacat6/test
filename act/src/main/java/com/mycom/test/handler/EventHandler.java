package com.mycom.test.handler;

import org.activiti.engine.delegate.event.ActivitiEvent;

/**
 * @author ：songdalin
 * @date ：2022/7/7 下午 3:32
 * @description：
 * @modified By：
 * @version: 1.0
 */
public interface EventHandler {

    void handler(ActivitiEvent event);
}
