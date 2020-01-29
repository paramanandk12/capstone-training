package com.mindtree.mystay.catalog.config;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@Configuration
public class RedisUtil<T> {

	private RedisTemplate<String, T> redisTemplate;
	private HashOperations<String, Object, T> hashOperation;

	@Autowired
	RedisUtil(RedisTemplate<String, T> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperation = redisTemplate.opsForHash();
	}

	public void putMap(String redisKey, Object key, T data) {
		hashOperation.put(redisKey, key, data);
	}

	public T getMapAsSingleEntry(String redisKey, Object key) {
		return hashOperation.get(redisKey, key);
	}

	public Map<Object, T> getMapAsAll(String redisKey) {
		return hashOperation.entries(redisKey);
	}

	public void clearCache(String redisKey, Object key) {
		hashOperation.delete(redisKey, key);
	}

	public void setExpire(String key, long timeout, TimeUnit unit) {
		redisTemplate.expire(key, timeout, unit);
	}

}
