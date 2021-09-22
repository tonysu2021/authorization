package com.auth.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.auth.common.conf.AuthConfig;
import com.auth.common.conf.SecretConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ SecretConfiguration.class, AuthConfig.class })
public @interface EnableAuth {

}
