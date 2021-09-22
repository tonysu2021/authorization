package com.auth.gateway.websocket;

import java.net.URI;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.common.domain.AuthDomain;
import com.commerce.cache.client.CacheManager;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import cn.hutool.http.HttpUtil;
import reactor.core.publisher.Mono;

@Service
public class AuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	private CacheManager<AuthDomain> cacheManager;

	@Autowired
	public AuthenticationService(CacheManager<AuthDomain> cacheManager) {
		this.cacheManager = cacheManager;
	}

	public Mono<Boolean> authenticate(URI uri) {
		String params = uri.getQuery();
		HashMap<String, String> paramMap = HttpUtil.decodeParamMap(params, "UTF-8");

		String accessToken = paramMap.get("access_token");
		if (StringUtils.isEmpty(accessToken)) {
			return Mono.just(Boolean.FALSE);
		}
		logger.info("access_token : {}", accessToken);

		try {
			JWT jwtToken = JWTParser.parse(accessToken);
			JWTClaimsSet jwtClaimsSet = jwtToken.getJWTClaimsSet();
			String clientId = jwtClaimsSet.getStringClaim("client_id");
			String authName = jwtClaimsSet.getSubject();

			logger.info("clientId : {}", clientId);
			logger.info("authName : {}", authName);

			return cacheManager.get(generateKey(clientId, authName), AuthDomain.class).map(authDomain -> {
				if (!StringUtils.contains(accessToken, authDomain.getAccessToken())) {
					logger.info("Acess Token  is inconsistent");
					return Boolean.FALSE;
				}
				logger.info("Acess Token  is consistent");
				return Boolean.TRUE;
			}).switchIfEmpty(Mono.just(Boolean.FALSE));

		} catch (ParseException e) {
			logger.error(e.getMessage());
			return Mono.just(Boolean.FALSE);
		}

	}

	private String generateKey(String clientId, String userName) {
		return String.format("%s-%s", clientId, userName);
	}
}
