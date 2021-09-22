package com.auth.common.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth.common.AuthClient;
import com.auth.common.domain.AuthDomain;
import com.auth.common.service.AuthService;
import com.commerce.cache.client.CacheManager;

@Configuration
public class AuthConfig {

	@Bean
	public AuthClient authClient(SecretConfiguration secret) {
		return new AuthClient(secret);
 	}
	
	@Bean("authService")
	@ConditionalOnBean(CacheManager.class)
	public AuthService authService(CacheManager<AuthDomain> cacheManager, AuthClient authClient) {
		return new AuthService(cacheManager, authClient);
	}
}
