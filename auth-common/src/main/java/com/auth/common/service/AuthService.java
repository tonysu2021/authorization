package com.auth.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.common.AuthClient;
import com.auth.common.domain.AuthDomain;

import reactor.core.publisher.Mono;

@Service("authService")
public class AuthService {

	@Autowired
	private AuthClient authClient;

	public AuthService(AuthClient authClient) {
		super();
		this.authClient = authClient;
	}

	public Mono<AuthDomain> getToken(String userName, String password) {
		return authClient.getToken(userName, password);
	}

	public Mono<AuthDomain> refreshToken(String refreshToken) {
		return authClient.refreshToken(refreshToken);

	}

	public Mono<Boolean> logout(String userName) {
		return authClient.logout(userName);
	}
}