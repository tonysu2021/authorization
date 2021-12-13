package com.auth.gateway.conf;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.auth.common.domain.AuthDomain;
import com.auth.gateway.auth.PermissionAuthManager;
import com.commerce.cache.client.CacheManager;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class SecurityConfig {
	
    @Autowired
    private CacheManager<AuthDomain> cacheManager;
	
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
		http
				.authorizeExchange(exchanges -> 
					exchanges
					// 直播-SRS
					.pathMatchers("/live-stream/srs_on_connect").permitAll()
					.pathMatchers("/live-stream/on_publish").permitAll()
					.pathMatchers("/live-stream/on_unpublish").permitAll()
					.pathMatchers("/live-stream/srs_on_close").permitAll()
					.pathMatchers("/live-stream/srs_on_dvr").permitAll()
					// 直播-聊天室
					.pathMatchers("/live-stream/chat").permitAll()
					// 直播-影片
					.pathMatchers("/live-stream/video").permitAll()
					.pathMatchers("/live-stream/upload").permitAll()
					.pathMatchers("/live-stream/ffmpeg").permitAll()
					.pathMatchers(HttpMethod.POST,"/live-stream/auth/login").permitAll()
					.pathMatchers("/live-stream/**").access(new PermissionAuthManager(cacheManager,"Live-Stream"))
					.anyExchange().authenticated()
					)
				.csrf().disable()
				.oauth2ResourceServer(
						oauth2ResourceServer -> oauth2ResourceServer.jwt().jwtAuthenticationConverter(this::convert));
		return http.build();
	}

	@SuppressWarnings("unchecked")
	private Mono<JwtAuthenticationToken> convert(Jwt jwt) {
		Collection<SimpleGrantedAuthority> authorities = ((Collection<String>) jwt.getClaims().get("authorities"))
				.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
		return Mono.just(new JwtAuthenticationToken(jwt, authorities));
	}
}