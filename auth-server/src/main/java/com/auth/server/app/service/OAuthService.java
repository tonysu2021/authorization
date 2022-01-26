package com.auth.server.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.auth.common.domain.AuthDomain;
import com.auth.server.infra.constant.BeanConstant;

@Service("oAuthService")
public class OAuthService {

	@Autowired
	@Qualifier(BeanConstant.REDIS_TEMPLATE)
	private RedisTemplate<String, AuthDomain> redisTemplate;

	public Boolean logout(String userName,String clientId) {
		return redisTemplate.delete(generateKey(clientId, userName, AuthDomain.class));
	}
	
	private String generateKey(String clientId, String userName, Class<?> clz) {
		return String.format("%s-%s-%s", clientId, userName, clz.getSimpleName());
	}
}
