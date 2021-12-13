package com.auth.server.infra.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.auth.common.domain.AuthDomain;
import com.auth.server.infra.constant.BeanConstant;
import com.auth.server.infra.util.ContextUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
public class AccessTokenAlterFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AccessTokenAlterFilter.class);

	private ObjectMapper objectMapper;

	private ContextUtil utils;

	private RedisTemplate<String, AuthDomain> redisTemplate;

	public AccessTokenAlterFilter(ObjectMapper objectMapper, ContextUtil utils,
			RedisTemplate<String, AuthDomain> redisTemplate) {
		super();
		this.objectMapper = objectMapper;
		this.utils = utils;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		CharResponseWrapper wrappedResponse = new CharResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request, wrappedResponse);
		byte[] bytes = wrappedResponse.getByteArray();
		AuthDomain resp = objectMapper.readValue(bytes, AuthDomain.class);
		String accessToken = resp.getAccessToken();
		response.getOutputStream().write(bytes);
		// 緩存至Redis
		TokenStore tokenStore = (TokenStore) utils.getBean(BeanConstant.TOKEN_STORE);
		OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
		logger.info("Response userName: {}", authentication.getName());
		logger.info("Response clientId: {}", authentication.getOAuth2Request().getClientId());
		logger.info("Response accessToken: {}", accessToken);
		logger.info("Response expire Time: {}", resp.getExpireTime());
		redisTemplate.opsForValue().set(generateKey(authentication.getOAuth2Request().getClientId(),
				authentication.getName(), AuthDomain.class), resp, resp.getExpireTime(), TimeUnit.SECONDS);
	}

	private String generateKey(String clientId, String userName, Class<?> clz) {
		return String.format("%s-%s-%s", clientId, userName, clz.getSimpleName());
	}

	private static class ByteArrayServletStream extends ServletOutputStream {
		ByteArrayOutputStream baos;

		ByteArrayServletStream(ByteArrayOutputStream baos) {
			this.baos = baos;
		}

		public void write(int param) throws IOException {
			baos.write(param);
		}

		@Override
		public boolean isReady() {
			return false;
		}

		@Override
		public void setWriteListener(WriteListener listener) {
			// Do nothing
		}
	}

	private static class ByteArrayPrintWriter {
		private ByteArrayOutputStream baos = new ByteArrayOutputStream();
		private PrintWriter pw = new PrintWriter(baos);
		private ServletOutputStream sos = new ByteArrayServletStream(baos);

		public PrintWriter getWriter() {
			return pw;
		}

		public ServletOutputStream getStream() {
			return sos;
		}

		byte[] toByteArray() {
			return baos.toByteArray();
		}
	}

	public class CharResponseWrapper extends HttpServletResponseWrapper {
		private ByteArrayPrintWriter output;
		private boolean usingWriter;

		public CharResponseWrapper(HttpServletResponse response) {
			super(response);
			usingWriter = false;
			output = new ByteArrayPrintWriter();
		}

		public byte[] getByteArray() {
			return output.toByteArray();
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			if (usingWriter) {
				super.getOutputStream();
			}
			usingWriter = true;
			return output.getStream();
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			if (usingWriter) {
				super.getWriter();
			}
			usingWriter = true;
			return output.getWriter();
		}

		public String toString() {
			return output.toString();
		}
	}

}
