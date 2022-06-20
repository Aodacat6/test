package com.mycom.redislock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.jic.hrois.common.core.constant.ServiceNameConstants.FINANCE_SERVICE;

/**
 * @author ：songdalin
 * @date ：2022/6/15 下午 6:25
 * @description：基于redis实现简易分布式锁
 * @modified By：
 * @version: 1.0
 */
@Slf4j
@Component
public class RedisLockUtil {

	/**
	 * 订单锁定、解锁 key 头部信息
	 */
	public static final String ORDER_LOCK_LOCK =  "ORDERLOCK:";

	/**
	 * 锁住1000 ms,实际观察得到handler方法处理时间一般在50ms左右
	 */
	private static final long ORDER_LOCK_EXPIRE_TIME = 1000L;

	@Autowired
	private RedisOpsUtil redisOpsUtil;

	/**
	 * 上锁
	 * @param key
	 * @return
	 */
	public synchronized boolean lock(String key) {
		if (redisOpsUtil.setNx(key, ORDER_LOCK_EXPIRE_TIME)) {
			log.info("订单：{} 成功加锁", key);
			return true;
		}
		//上锁失败，等待锁，最大等待50次，防止死锁导致死循环
		int retryCount = 50;
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (redisOpsUtil.setNx(key, ORDER_LOCK_EXPIRE_TIME)) {
				log.info("订单：{} 重试 {}次后成功加锁", key, retryCount);
				return true;
			}
			//次数-1
			retryCount--;
		} while (retryCount > 0);
		log.info("订单：{} 尝试加锁失败", key);
		return false;
	}

	/**
	 * 解锁
	 * @param key
	 * @return
	 */
	public synchronized boolean unLock(String key) {
		if (redisOpsUtil.delKey(key)) {
			log.info("{} 已解锁", key);
			return true;
		}
		return !redisOpsUtil.existsKey(key);
	}


}
