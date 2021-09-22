package com.auth.gateway.websocket;

import java.net.URI;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.socket.HandshakeInfo;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.adapter.ReactorNettyWebSocketSession;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.http.server.WebsocketServerSpec;

@SuppressWarnings("deprecation")
public class AuthRequestUpgradeStrategy extends ReactorNettyRequestUpgradeStrategy {

	private static final Logger logger = LoggerFactory.getLogger(AuthRequestUpgradeStrategy.class);

	private final Supplier<WebsocketServerSpec.Builder> specBuilderSupplier;

	@Nullable
	private Integer maxFramePayloadLength;

	@Nullable
	private Boolean handlePing;

	private final AuthenticationService service;

	public AuthRequestUpgradeStrategy(Supplier<WebsocketServerSpec.Builder> builderSupplier,
			AuthenticationService service) {
		this.specBuilderSupplier = builderSupplier;
		this.service = service;
	}

	@Override
	public Mono<Void> upgrade(ServerWebExchange exchange, WebSocketHandler handler, @Nullable String subProtocol,
			Supplier<HandshakeInfo> handshakeInfoFactory) {

		ServerHttpResponse response = exchange.getResponse();
		HttpServerResponse reactorResponse = ServerHttpResponseDecorator.getNativeResponse(response);
		HandshakeInfo handshakeInfo = handshakeInfoFactory.get();
		NettyDataBufferFactory bufferFactory = (NettyDataBufferFactory) response.bufferFactory();
		URI uri = exchange.getRequest().getURI();

		return service.authenticate(uri).filter(Boolean::booleanValue).doOnNext(result -> logger.info("AUTHORIZED"))
				.flatMap(result -> {
					WebsocketServerSpec spec = genSpec(subProtocol);
					return reactorResponse.sendWebsocket((in, out) -> {
						ReactorNettyWebSocketSession session = new ReactorNettyWebSocketSession(in, out, handshakeInfo,
								bufferFactory, getMaxFramePayloadLength());
						return handler.handle(session);
					}, spec);
				}).switchIfEmpty(Mono.just("UNATHORIZED").doOnNext(logger::info).then());

	}

	WebsocketServerSpec genSpec(@Nullable String subProtocol) {
		WebsocketServerSpec.Builder builder = this.specBuilderSupplier.get();
		if (subProtocol != null) {
			builder.protocols(subProtocol);
		}
		if (this.maxFramePayloadLength != null) {
			builder.maxFramePayloadLength(this.maxFramePayloadLength);
		}
		if (this.handlePing != null) {
			builder.handlePing(this.handlePing);
		}
		return builder.build();
	}
}