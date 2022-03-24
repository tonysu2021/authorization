package com.auth.server.infra.redis;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
@AutoConfigureBefore(RedisTemplateConfig.class)
public class RedisLockConfig {

	private static final String REGISTRY_KEY = "REDIS_DISTRIBUTION_LOCK";

	private static final long EXPIRE_AFTER = 60000L;

	/**
	 * RedisLockRegistry 本身具有本地鎖及 RLock 功能。
	 * */
	@Bean
	public RedisLockRegistry redisLockRegistry(LettuceConnectionFactory connectionFactory) {
		return new RedisLockRegistry(connectionFactory, REGISTRY_KEY, EXPIRE_AFTER);
	}
}
