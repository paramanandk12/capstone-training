package com.mindtree.mystay.catalog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Abhishek Karmakar M1049319
 *
 */
@Configuration
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String redisHostname;
	@Value("${spring.redis.password}")
	private String redisPwd;
	@Value("${spring.redis.port}")
	private Integer redisPort;

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	protected JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName(redisHostname);
		configuration.setPassword(redisPwd);
		configuration.setPort(redisPort);
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);
		factory.afterPropertiesSet();
		return factory;
	}
}