package com.auth.server.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.auth.common.domain.AuthDomain;
import com.auth.server.infra.constant.BeanConstant;
import com.auth.server.infra.util.ContextUtil;

@SuppressWarnings("deprecation")
@Service("oAuthService")
public class OAuthService {

	@Autowired
	@Qualifier(BeanConstant.REDIS_TEMPLATE)
	private RedisTemplate<String, AuthDomain> redisTemplate;

	@Autowired
	@Qualifier(BeanConstant.CONTEXT_UTIL)
	private ContextUtil utils;

	public Boolean logout(String userName, String clientId) {
		String accessTokenKey = generateKey(clientId, userName, AuthDomain.class);
		AuthDomain authDomain = redisTemplate.opsForValue().get(accessTokenKey);
		if (authDomain == null) {
			return Boolean.FALSE;
		}
		TokenStore tokenStore = (TokenStore) utils.getBean(BeanConstant.TOKEN_STORE);
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(authDomain.getAccessToken());
		tokenStore.removeAccessToken(accessToken);
		redisTemplate.delete(accessTokenKey);
		return Boolean.TRUE;
	}

	private String generateKey(String clientId, String userName, Class<?> clz) {
		return String.format("%s-%s-%s", clientId, userName, clz.getSimpleName());
	}
}
