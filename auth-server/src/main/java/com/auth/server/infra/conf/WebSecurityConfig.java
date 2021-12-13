package com.auth.server.infra.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth.common.domain.AuthDomain;
import com.auth.server.infra.constant.BeanConstant;
import com.auth.server.infra.filter.AccessTokenAlterFilter;
import com.auth.server.infra.util.ContextUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/user").antMatchers("/user/*").antMatchers("/oauth/logout").antMatchers("/oauth/check_token")
				.antMatchers("/introspect").mvcMatchers("/.well-known/jwks.json");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return mapper;
	}

	@Bean
	@ConditionalOnBean(RedisTemplate.class)
	public AccessTokenAlterFilter accessTokenAlterFilter(ObjectMapper objectMapper,
			@Qualifier(BeanConstant.CONTEXT_UTIL) ContextUtil utils,
			@Qualifier(BeanConstant.REDIS_TEMPLATE) RedisTemplate<String, AuthDomain> redisTemplate) {
		return new AccessTokenAlterFilter(objectMapper, utils, redisTemplate);
	}

	@Bean
	public FilterRegistrationBean<AccessTokenAlterFilter> sessionTimeoutFilter(
			AccessTokenAlterFilter accessTokenAlterFilter) {
		FilterRegistrationBean<AccessTokenAlterFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(accessTokenAlterFilter);
		registrationBean.addUrlPatterns("/oauth/token");
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
