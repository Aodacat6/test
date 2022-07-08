package com.mycom.test.config;

import com.mycom.test.listener.GlobalTaskListener;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：songdalin
 * @date ：2022/7/7 下午 2:59
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Component
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Autowired
    private GlobalTaskListener taskListenerr;

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        /*springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");*/
        //springProcessEngineConfiguration.setDbIdentityUsed(false);
        //springProcessEngineConfiguration.setIdGenerator(new MyDbIdGenerator());
        List<ActivitiEventListener> eventListeners=new ArrayList<>();
        eventListeners.add(taskListenerr);
        springProcessEngineConfiguration.setEventListeners(eventListeners);
    }
}
