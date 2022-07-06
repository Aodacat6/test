package com.test.idempotencectrl.annotation;

import java.lang.annotation.*;

/**
 * @author ：songdalin
 * @date ：2022/7/6 上午 10:05
 * @description：接口幂等性控制注解
 * 					根据定义的接口入参和访问控制时间，如果控制时间内多次使用定义的入参进行请求，则对接口进行幂等处理
 * @modified By：
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdempotenceCtrl {

	/**
	 * 自定义访问控制时间，单位毫秒，默认值1000
	 * @return
	 */
	long intervalTime() default 1000;

	/**
	 * 自定义入参中某几个字段为业务主键，只判断业务主键是否相同，默认为对整个入参比较
	 * @return
	 */
	//String[] businessKeys() default {};

	/**
	 * 需要比较的业务入参类，用于多个入参的情况
 	 * @return
	 */
	Class[] markClass() default {};
}
