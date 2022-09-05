/*
package com.mycom.test.aop;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

*/
/**
 * @author ：songdalin
 * @date ：2022/6/22 下午 1:55
 * @description：
 * @modified By：
 * @version: 1.0
 *//*

@Component
@Aspect
public class AspectTest {


    @Around("execution(* com.mycom.test.controller.TestController.hello(..)) " +
            " || execution(* com.mycom.test.controller.TestController.lock(..))" +
            " || execution(* com.mycom.test.controller.TestController.get(..))" +
            " || execution(* com.mycom.test.service.TestService.hhhh(..))")
    public void pointCut(ProceedingJoinPoint joinPoin) {
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
}
*/
