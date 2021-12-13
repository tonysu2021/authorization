package com.auth.server.infra.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.auth.server.infra.constant.BeanConstant;

@Component(BeanConstant.CONTEXT_UTIL)
public class ContextUtil implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public  Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public  <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public  boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public  boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	public  Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}

}