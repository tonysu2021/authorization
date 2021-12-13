package com.auth.common.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth.common.AuthClient;
import com.auth.common.service.AuthService;

@Configuration
public class AuthConfig {

	@Bean
	public AuthClient authClient(SecretConfiguration secret) {
		return new AuthClient(secret);
	}

	@Bean("authService")
	public AuthService authService(AuthClient authClient) {
		return new AuthService(authClient);
	}
}
