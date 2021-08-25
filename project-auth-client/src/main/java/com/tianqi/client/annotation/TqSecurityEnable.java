package com.tianqi.client.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/22 15:44
 * @Description:
 */
@EnableWebSecurity
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@ComponentScan("com.tianqi.client")
@Target(value = ElementType.TYPE)
public @interface TqSecurityEnable {
}
