package com.test.idempotencectrl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.test.idempotencectrl.annotation.IdempotenceCtrl;
import com.test.util.RedisOpsUtil;

import java.util.Arrays;
import java.util.Optional;


/**
 * @author ：songdalin
 * @date ：2022/7/6 上午 10:24
 * @description：幂等注解切面，实现具体控制操作
 * @modified By：
 * @version: 1.0
 */
@Slf4j
@Aspect
@Component
public class IdempotenceCtrlAspect {

	@Autowired
	private RedisOpsUtil redisOpsUtil;

	/**
	 * 自定义时间内（1s），如果redis中已记录，那么不处理，直接返回
	 * @return
	 */
	@Around("@annotation(idempotenceCtrl)")
	public Object pointAround(ProceedingJoinPoint joinPoin, IdempotenceCtrl idempotenceCtrl) throws Throwable {
		//定义的访问控制时间
		final long intervalTime = idempotenceCtrl.intervalTime();
		//todo 定义的需要判断的业务主键 暂未实现，当前只比较全部入参
		//final String[] businessKeys = idempotenceCtrl.businessKeys();
		final Class[] classes = idempotenceCtrl.markClass();
		//参数校验
		Object arg;
		paramCheck(classes, joinPoin);
		if (joinPoin.getArgs().length == 1) {
			//注解的方法只有1个参数
			arg = joinPoin.getArgs()[0];
		}else {
			//注解的方法有多个参数，按配置选中一个标记
			final Optional<Object> optional = Arrays.stream(joinPoin.getArgs()).filter(f -> f.getClass() == classes[0]).findAny();
			if (!optional.isPresent()) {
				throw new RuntimeException("not exists params of type " + classes[0].getName());
			}
			arg = optional.get();
		}
		Signature s = joinPoin.getSignature();
		MethodSignature ms = (MethodSignature)s;
		//判断是否被幂等控制
		if (!CompareAndSetMark(ms.getName(), arg, intervalTime)) {
			//不执行方法，直接返回
			log.info("接口：{} ，参数：{} 幂等校验失败", ms.getName(), arg);
			return "e";
		}
		Object proceed = joinPoin.proceed();
		return proceed;
	}

	/**
	 * 使用 请求方法名 + 请求参数hashcode 做标记。
	 * 		成功标记的话，返回true
	 * 		其他失败情况，返回false
	 * @param methodName
	 * @param arg
	 * @param intervalTime
	 * @return
	 */
	private boolean CompareAndSetMark(String methodName, Object arg, long intervalTime) {
		if (arg == null) {
			//不处理入参为空的情况，直接放行
			return true;
		}
		//用hashcode做相等判断
		final String key = RedisOpsUtil.IDEMPOTENCE_CTRL_KEY + methodName + arg.hashCode();
		if (!redisOpsUtil.existsKey(key)) {
			synchronized (this) {
				if (!redisOpsUtil.existsKey(key)) {
					redisOpsUtil.setNx(key, intervalTime);
					//只有成功设置了标记，才返回true
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 注解参数 和 方法参数校验
	 * @param classes
	 * @param joinPoin
	 */
	private void paramCheck(Class[] classes, ProceedingJoinPoint joinPoin) {
		//todo 需要比较的入参类型，目前仅支持多个入参选一个
		if (classes.length > 1) {
			throw new RuntimeException("@IdempotenceCtrl 注解markClass属性暂不支持多参数");
		}
		if (joinPoin.getArgs() == null || joinPoin.getArgs().length == 0) {
			throw new RuntimeException("@IdempotenceCtrl 注解的方法必要要有输入参数");
		}
		//如果有多个入参，那么注解必须指定用哪一个
		if (joinPoin.getArgs().length > 1 && classes.length == 0) {
			throw new RuntimeException("@IdempotenceCtrl 注解注解在多参数方法，必须指定一个标记类");
		}
	}

}
