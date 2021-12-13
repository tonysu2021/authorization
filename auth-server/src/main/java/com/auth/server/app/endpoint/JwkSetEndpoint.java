package com.auth.server.app.endpoint;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

@SuppressWarnings("deprecation")
@FrameworkEndpoint
public class JwkSetEndpoint {
	
	private static final Logger logger = LoggerFactory.getLogger(JwkSetEndpoint.class);
	
	KeyPair keyPair;

	JwkSetEndpoint(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	@GetMapping("/.well-known/jwks.json")
	@ResponseBody
	public Map<String, Object> getKey() {
		logger.info("getKey....");
		RSAPublicKey publicKey = (RSAPublicKey) this.keyPair.getPublic();
		RSAKey key = new RSAKey.Builder(publicKey).build();
		return new JWKSet(key).toJSONObject();
	}
}
