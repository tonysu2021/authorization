package com.auth.server.infra.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth.server.infra.intercepter.TokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private TokenInterceptor tokenInterceptor;

	/**
	 * 註冊攔截器
	 *
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor)
				// Add need to check
				.addPathPatterns("/user").addPathPatterns("/user/**")
				.excludePathPatterns("/user/register");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://127.0.0.1:8080","http://localhost:8080")
				.allowCredentials(true)
				.allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE")
				.allowedHeaders("*");
	}

}
