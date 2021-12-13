package com.auth.server.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.common.domain.AuthDomain;
import com.auth.server.infra.constant.BeanConstant;

@RestController
@RequestMapping(value = "/oauth")
public class OAuthController {

	@Autowired
	@Qualifier(BeanConstant.REDIS_TEMPLATE)
	private RedisTemplate<String, AuthDomain> redisTemplate;

	@DeleteMapping(value = "/logout")
	public ResponseEntity<Boolean> logout(@RequestParam(name = "username") String userName,
			@RequestParam(name = "clientId") String clientId) {
		return ResponseEntity.ok().body(redisTemplate.delete(generateKey(clientId, userName, AuthDomain.class)));
	}

	private String generateKey(String clientId, String userName, Class<?> clz) {
		return String.format("%s-%s-%s", clientId, userName, clz.getSimpleName());
	}
}
