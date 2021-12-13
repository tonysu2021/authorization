package com.auth.server.infra.intercepter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth.server.infra.constant.BeanConstant;
import com.auth.server.infra.exception.InvalidTokenException;
import com.auth.server.infra.util.ContextUtil;

@SuppressWarnings("deprecation")
@Component
public class TokenInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	private static final String CLIENT_ID = "admin";
	
	@Autowired
	@Qualifier(BeanConstant.CONTEXT_UTIL)
	private ContextUtil utils;

	/**
	 * preHandle
	 * 
	 * @param request  API請求
	 * @param response API回應
	 * @param handler
	 * @return
	 * @throws InvalidTokenException
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!StringUtils.equals(HttpMethod.OPTIONS.name(), request.getMethod())) {
			String accessTokenInFront = request.getHeader("Authorization");
			if (StringUtils.isEmpty(accessTokenInFront)) {
				throw new InvalidTokenException("Access Token is empty.");
			}
			TokenStore tokenStore = (TokenStore) utils.getBean(BeanConstant.TOKEN_STORE);
			OAuth2Authentication authentication = tokenStore.readAuthentication(accessTokenInFront);
			if (authentication == null) {
				throw new InvalidTokenException("Authentication no found in Database.");
			}
			List<OAuth2AccessToken> tokens = (List<OAuth2AccessToken>) tokenStore.findTokensByClientIdAndUserName(CLIENT_ID,
					authentication.getName());
			if (CollectionUtils.isEmpty(tokens)) {
				throw new InvalidTokenException("Access Token no found in Database.");
			}
			String accessTokenInDB = tokens.get(0).getValue();
			if (!StringUtils.equals(accessTokenInFront, accessTokenInDB)) {
				throw new InvalidTokenException("Acess Token  is inconsistent.");
			}
			logger.info("Acess Token  is consistent");
		}
		return true;
	}

}
