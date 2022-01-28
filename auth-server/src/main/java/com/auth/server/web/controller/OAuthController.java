package com.auth.server.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.server.app.service.OAuthService;

@RestController
@RequestMapping(value = "/oauth")
public class OAuthController {

	@Autowired
	@Qualifier("oAuthService")
	private OAuthService oAuthService;

	@DeleteMapping(value = "/logout")
	public ResponseEntity<Boolean> logout(@RequestParam(name = "username") String userName,
			@RequestParam(name = "clientId") String clientId) {
		return ResponseEntity.ok().body(oAuthService.logout(userName, clientId));
	}
}
