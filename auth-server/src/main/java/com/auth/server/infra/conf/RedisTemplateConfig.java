package com.auth.server.infra.conf;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.auth.server.infra.constant.BeanConstant;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
@AutoConfigureAfter(RedisCacheConfig.class)
public class RedisTemplateConfig {

	@Bean(BeanConstant.JACKSON_SERIALIZER)
	public Jackson2JsonRedisSerializer<Object> jacksonSerializer() {
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
				JsonTypeInfo.As.WRAPPER_ARRAY);

		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		serializer.setObjectMapper(objectMapper);

		return serializer;
	}

	@Bean(BeanConstant.REDIS_TEMPLATE)
	public <V> RedisTemplate<String, V> redisTemplate(LettuceConnectionFactory connectionFactory,
			Jackson2JsonRedisSerializer<Object> jacksonSerializer) {
		RedisTemplate<String, V> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(jacksonSerializer);
		return redisTemplate;
	}
}
