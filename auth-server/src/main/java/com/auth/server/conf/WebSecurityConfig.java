package com.auth.server.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				// path:/user 需要最高權限
				.antMatchers("/user")
				.antMatchers("/user/*")
				.antMatchers("/oauth/check_token")
				.antMatchers("/introspect")
				.mvcMatchers("/.well-known/jwks.json");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {

			private BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

			@Override
			public String encode(CharSequence charSequence) {
				String hashPass = bcryptPasswordEncoder.encode(charSequence);
				logger.info("[encode] pass : {}", charSequence);
				logger.info("[encode] hashPass: {}", hashPass);
				return hashPass;
			}

			@Override
			public boolean matches(CharSequence charSequence, String hashPass) {
				boolean isMatch = bcryptPasswordEncoder.matches(charSequence, hashPass);
				logger.info("[matches] pass : {}", charSequence);
				logger.info("[matches] hashPass: {}", hashPass);
				logger.info("[matches] matches: {}", isMatch);
				return isMatch;
			}
		};
	}
}
