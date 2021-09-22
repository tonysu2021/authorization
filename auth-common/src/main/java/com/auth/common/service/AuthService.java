package com.auth.common.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.common.AuthClient;
import com.auth.common.domain.AuthDomain;
import com.commerce.cache.client.CacheManager;

import reactor.core.publisher.Mono;

@Service("authService")
public class AuthService {

	private Logger logger = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	private CacheManager<AuthDomain> cacheManager;

	@Autowired
	private AuthClient authClient;
	
	public AuthService(CacheManager<AuthDomain> cacheManager, AuthClient authClient) {
		super();
		this.cacheManager = cacheManager;
		this.authClient = authClient;
	}

	public Mono<AuthDomain> getToken(String userName, String password) {
		return authClient.getToken(userName, password)
				.flatMap(data -> cacheManager.save(generateKey(userName), data).map(i -> data));
	}

	public Mono<AuthDomain> refreshToken(String userName, String accessToken) {
		return cacheManager.get(generateKey(userName), AuthDomain.class).flatMap(authDomain -> {
			logger.info("[front] accessToken : {}", accessToken);
			logger.info("[cache] accessToken : {}", authDomain.getAccessToken());
			if (!StringUtils.contains(accessToken, authDomain.getAccessToken())) {
				logger.info("Acess Token  is inconsistent");
				return Mono.empty();
			}
			logger.info("Acess Token  is consistent");
			return authClient.refreshToken(authDomain.getRefreshToken())
					.flatMap(data -> cacheManager.save(generateKey(userName), data).map(i -> data));
		});
	}
	
	public Mono<Boolean> logout(String userName) {
		return cacheManager.delete(generateKey(userName), AuthDomain.class);
	}
	
	private String generateKey(String userName) {
		return String.format("%s-%s",authClient.getClientId(), userName);
	}
}