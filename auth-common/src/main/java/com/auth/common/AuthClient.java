package com.auth.common;

import java.time.Duration;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.auth.common.conf.SecretConfiguration;
import com.auth.common.domain.AuthDomain;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class AuthClient  {

	private static Logger logger = LoggerFactory.getLogger(AuthClient.class);
	
	private static final String AUTH_URL = "http://auth-server:10083";

	private Retry retry;
	
	private WebClient client;
	
	private SecretConfiguration secret;

	public AuthClient(SecretConfiguration secret) {
		this.secret = secret;
		this.client = createWebClient(AUTH_URL);
		// 重試機制-使用背壓模式重試1次間隔500毫秒
		this.retry = Retry.backoff(1, Duration.ofMillis(500))
						.doAfterRetry(rs -> logger.debug("Retried at {}" , LocalTime.now()))
						.onRetryExhaustedThrow((spec, rs) -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		logger.info("{}",secret);
	}

	private WebClient createWebClient(String baseUrl) {
		return WebClient.builder().baseUrl(baseUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
	
	
	public Mono<AuthDomain> getToken(String username, String password) {
		return getToken(username, password, "password", "read");
	}

	public Mono<AuthDomain> getToken(String username, String password, String grantType, String scope) {
		String encodedClientData = Base64Utils.encodeToString(getAuthBasic().getBytes());
		
		StringBuilder tokenUriStr = new StringBuilder("/oauth/token");
		tokenUriStr.append("?grant_type=").append(grantType);
		tokenUriStr.append("&username=").append(username);
		tokenUriStr.append("&password=").append(password);
		tokenUriStr.append("&scope=").append(scope);
		return client.post().uri(tokenUriStr.toString())
				.header("Authorization", "Basic " + encodedClientData)
				.retrieve().bodyToMono(AuthDomain.class)
				.retryWhen(retry);
	}

	public Mono<AuthDomain> refreshToken(String refreshToken) {
		String encodedClientData = Base64Utils.encodeToString(getAuthBasic().getBytes());
		
		StringBuilder tokenUriStr = new StringBuilder("/oauth/token");
		tokenUriStr.append("?grant_type=").append("refresh_token");
		tokenUriStr.append("&refresh_token=").append(refreshToken);
		return client.post().uri(tokenUriStr.toString())
				.header("Authorization", "Basic " + encodedClientData)
				.retrieve().bodyToMono(AuthDomain.class).log("refreshToken")
				.retryWhen(retry);
	}
	
	public Mono<Boolean> logout(String userName){
		StringBuilder logoutUriStr = new StringBuilder("/oauth/logout");
		logoutUriStr.append("?username=").append(userName);
		logoutUriStr.append("&clientId=").append(secret.getClientId());
		return client.delete().uri(logoutUriStr.toString())
				.retrieve().bodyToMono(Boolean.class)
				.retryWhen(retry);
	}
	
	private String getAuthBasic() {
		StringBuilder authBasicStr = new StringBuilder();
		authBasicStr.append(secret.getClientId()).append(":").append(secret.getClientSecret());
		return authBasicStr.toString();
	}
	
	public String getClientId() {
		return secret.getClientId();
	}

}
