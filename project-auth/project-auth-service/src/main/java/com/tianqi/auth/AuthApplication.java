package com.tianqi.auth;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.tianqi.common.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;

/**
 * AuthApplication主入口函数
 *
 * @author yuantianqi
 * @since 2021-04-11 17:33:57
 */
@SpringBootApplication(scanBasePackages = {"com.tianqi.common", "com.tianqi.auth"})
@MapperScan("com.tianqi.auth.dao")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}

