package com.auth.gateway.auth;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.authorization.AuthorizationContext;

import com.auth.common.domain.AuthDomain;
import com.commerce.cache.client.CacheManager;

import reactor.core.publisher.Mono;

public class PermissionAuthManager implements ReactiveAuthorizationManager<AuthorizationContext> {

	private Logger logger = LoggerFactory.getLogger(PermissionAuthManager.class);

	private final List<String> authorities;
	
	private CacheManager<AuthDomain> cacheManager;
	
	public PermissionAuthManager(CacheManager<AuthDomain> cacheManager,String... authorities) {
		super();
		this.authorities = Arrays.asList(authorities);
		this.cacheManager = cacheManager;
	}

	@Override
	public Mono<AuthorizationDecision> check(Mono<Authentication> authenticationMono,
			AuthorizationContext authorizationContext) {

		return authenticationMono.flatMap(this::checkAuthorities)
				.defaultIfEmpty(new AuthorizationDecision(false));
	}

	private Mono<AuthorizationDecision> checkAuthorities(Authentication auth) {
		Jwt principal = (Jwt) auth.getPrincipal();

		if (principal == null) {
			return Mono.just(new AuthorizationDecision(false));
		}
		String clientId = principal.getClaimAsString("client_id");
		boolean isMatch = auth.getAuthorities().stream()
								.map(GrantedAuthority::getAuthority)
								.anyMatch(this.authorities::contains);
		if(!isMatch) {
			logger.info("Insufficient authority");
			return Mono.just(new AuthorizationDecision(false));
		}
		return cacheManager.get(generateKey(clientId,auth.getName()), AuthDomain.class).map(authDomain -> {
			logger.info("token {}",principal.getTokenValue());
			logger.info("cache {}",authDomain.getAccessToken());
			if (!StringUtils.contains(principal.getTokenValue(), authDomain.getAccessToken())) {
				logger.info("Acess Token  is inconsistent");
				return new AuthorizationDecision(false);
			}
			logger.info("Acess Token  is consistent");
			return new AuthorizationDecision(true);
		}).switchIfEmpty(Mono.just(new AuthorizationDecision(false)));
	}
	
	private String generateKey(String clientId,String userName) {
		return String.format("%s-%s",clientId, userName);
	}
}
