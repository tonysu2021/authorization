package com.auth.server.infra.redis;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import com.auth.server.infra.constant.BeanConstant;

@Configuration
@EnableCaching
public class RedisCacheConfig {

	@Autowired
	@Qualifier(BeanConstant.JACKSON_SERIALIZER)
	private Jackson2JsonRedisSerializer<Object> jacksonSerializer;

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
		return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(factory),
				redisCacheConfigurationWithTtl(60 * 10), this.getRedisCacheConfigurationMap());
	}

	private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
		for (RedisCacheKey redisCacheKey : RedisCacheKey.values()) {
			redisCacheConfigurationMap.put(redisCacheKey.getName(),
					redisCacheConfigurationWithTtl(redisCacheKey.getKeepSecond()));
		}
		return redisCacheConfigurationMap;
	}

	private RedisCacheConfiguration redisCacheConfigurationWithTtl(Integer seconds) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		if (seconds <= 0) {
			return redisCacheConfiguration
					.serializeValuesWith(SerializationPair.fromSerializer(jacksonSerializer));
		}
		return redisCacheConfiguration
				.serializeValuesWith(SerializationPair.fromSerializer(jacksonSerializer))
				.entryTtl(Duration.ofSeconds(seconds));
	}
}
