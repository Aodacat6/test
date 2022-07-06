package com.mycom.aop;

import cn.hutool.json.JSONUtil;
import com.mycom.client.TestClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ：songdalin
 * @date ：2022/6/22 下午 1:55
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Component
@Aspect
public class AspectTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    //@Around("execution(* com.mycom.controller.TestController.test(..)) " )
    @Around("execution(* com.mycom.client.TestClient.test(..))")
    public void pointCut(ProceedingJoinPoint joinPoin) throws Exception {

        final TestClient bean = applicationContext.getBean(TestClient.class);
        final Object target = getTarget(bean);
        final Method test = target.getClass().getMethod("test");
        final Object invoke = test.invoke(target);

        Signature s = joinPoin.getSignature();
        MethodSignature ms = (MethodSignature)s;
        StringBuilder paraBuilder = new StringBuilder();
        if (ms.getParameterTypes() != null && ms.getParameterTypes().length > 0) {
            for (Class parameterType : ms.getParameterTypes()) {
                paraBuilder.append(parameterType.getName()).append(";");
            }
        }
        if (paraBuilder.length() > 0) {
            paraBuilder.deleteCharAt(paraBuilder.length() - 1);
        }
        String paraTypeNames = paraBuilder.toString();

        final String path = joinPoin.getTarget().getClass().getName();

        final String name = ms.getName();

        StringBuilder sb = new StringBuilder();
        if (joinPoin.getArgs() != null && joinPoin.getArgs().length > 0) {
            for (Object arg : joinPoin.getArgs()) {
                sb.append(JSONUtil.toJsonStr(arg)).append(";");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        final String s1 = sb.toString();
        System.out.println(s1);

        //System.out.println(joinPoin.getTarget().toString());
        System.out.println("jinru");
//        String path = getClassPath(joinPoin.getSignature().toLongString());
//        String name = getMethodName(joinPoin.getSignature().toLongString());
        System.out.println(path);
        System.out.println(name);
        System.out.println(paraTypeNames);
//        System.out.println(getParamClass(joinPoin.getSignature().toLongString()));
        return;
    }

    /**
     * 获取 目标对象
     * @param proxy 代理对象
     * @return
     * @throws Exception
     */
    public static Object getTarget(Object proxy) throws Exception {

        if(!AopUtils.isAopProxy(proxy)) {
            return proxy;//不是代理对象
        }

        if(AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else { //cglib
            return getCglibProxyTargetObject(proxy);
        }

    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

        return target;
    }

    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();

        return target;
    }

    private String getClassPath(String signature) {
        final String s = signature.split(" ")[2];
        String aa = s.substring(0, s.lastIndexOf("("));
        return aa.substring(0, aa.lastIndexOf("."));
    }

    private String getMethodName(String signature) {
        final String s = signature.split(" ")[2];
        String aa = s.substring(0, s.lastIndexOf("("));
        return aa.substring(aa.lastIndexOf(".") + 1);
    }

    private String getParamClass(String signature) {
        final String s = signature.split(" ")[2];
        return s.substring(s.lastIndexOf("(") + 1, s.lastIndexOf(")"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
