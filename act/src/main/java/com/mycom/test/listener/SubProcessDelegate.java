package com.mycom.test.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author ：songdalin
 * @date ：2022/7/8 下午 2:23
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class SubProcessDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println(".........进入SubProcessDelegate............");
    }
}
