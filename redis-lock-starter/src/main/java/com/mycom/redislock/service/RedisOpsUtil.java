package com.mycom.redislock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.jic.hrois.common.core.constant.ServiceNameConstants.FINANCE_SERVICE;

/**
 * @author ：songdalin
 * @date ：2022/6/7 下午 6:42
 * @description：redis 缓存操作
 * @modified By：
 * @version: 1.0
 */
@Component
public class RedisOpsUtil {

	/**
	 * 有效期，一年
	 */
	public static final Long ONE_YEAR = 365 * 24 * 60 * 60L;

	/**
	 * 港前海运险- 调用物流服务失败数据保存
	 */

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * ================================================================
	 *              redis SET 通用操作
	 * ================================================================
	 */
	/**
	 * SET
	 * 放置一个元素
	 * @param key
	 * @param expire 过期时间 单位 秒
	 * @param value
	 */
	public void addElement2Set(@NotNull String key, long expire, @NotNull String... value) {
		redisTemplate.opsForSet().add(key, value);
		expireByKey(key, expire);
	}

	/**
	 * SET
	 * 获取所有元素
	 * @param key
	 * @return
	 */
	public Set<String> getSet(@NotNull String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * SET
	 * 移除某个元素
	 * @param key
	 * @param value
	 * @return 返回成功移除元素的个数
	 */
	public long removeElementSet(String key, String value) {
		return redisTemplate.opsForSet().remove(key, value);
	}

	/**
	 * SET
	 * 是否存在某个元素
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean existsSet(String key, String value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}



	/**
	 * ================================================================
	 *              redis通用操作
	 * ================================================================
	 */
	/**
	 * 设置元素过期时间
	 * @param key
	 * @param s    秒
	 * @return
	 */
	public boolean expireByKey(String key, long s) {
		return redisTemplate.expire(key, s, TimeUnit.SECONDS);
	}

	/**
	 * 是否存在某个key
	 * @param key
	 * @return
	 */
	public boolean existsKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 上锁,锁住 n ms
	 *      **  直接锁，不需要通过是否存在判断，只通过锁的结果判断是否成功
	 * @param key
	 * @return
	 */
	public boolean setNx(String key, long n) {
		//set key value EX n NX    n ms
		return redisTemplate.opsForValue().setIfAbsent(key, "1", Duration.ofMillis(n));
	}

	/**
	 * 删除key
	 * @param key
	 * @return
	 */
	public boolean delKey(String key) {
		return redisTemplate.delete(key);
	}

}
