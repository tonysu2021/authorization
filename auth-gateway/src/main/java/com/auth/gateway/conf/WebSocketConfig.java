package com.auth.gateway.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.server.RequestUpgradeStrategy;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import com.auth.gateway.websocket.AuthRequestUpgradeStrategy;
import com.auth.gateway.websocket.AuthenticationService;

import reactor.netty.http.server.WebsocketServerSpec;

@Configuration
public class WebSocketConfig  {
	
	@Bean
	public RequestUpgradeStrategy requestUpgradeStrategy(AuthenticationService authenticationService) {
		WebsocketServerSpec.Builder builder = WebsocketServerSpec.builder().maxFramePayloadLength(819200)
				.handlePing(true);
		return new AuthRequestUpgradeStrategy(builder, authenticationService);
	}

	@Bean
	public WebSocketHandlerAdapter handlerAdapter(WebSocketService webSocketService) {
		return new WebSocketHandlerAdapter(webSocketService);
	}

}
