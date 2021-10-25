package com.tianqi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/22 08:47
 * @Description:
 */
@SpringBootApplication(scanBasePackages = {"com.tianqi.gateway"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.tianqi.*.api"})
@EnableCircuitBreaker
public class GatewayApplication {
    public static void main(final String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
