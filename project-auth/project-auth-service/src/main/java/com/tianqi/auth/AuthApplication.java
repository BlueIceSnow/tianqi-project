package com.tianqi.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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

