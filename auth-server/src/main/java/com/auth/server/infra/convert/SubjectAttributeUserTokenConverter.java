package com.auth.server.infra.convert;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

@SuppressWarnings("deprecation")
public class SubjectAttributeUserTokenConverter extends DefaultUserAuthenticationConverter {
	@Override
	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("sub", authentication.getName());
		if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
			response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}
		return response;
	}
}
