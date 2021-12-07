package com.tianqi.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * MovieApplication主入口函数
 *
 * @Author yuantianqi
 * @since 2021-04-11 18:37:17
 */
@SpringBootApplication(scanBasePackages = {"com.tianqi.common", "com.tianqi.movie"})
@EnableFeignClients(basePackages = "com.tianqi.*.api")
@MapperScan("com.tianqi.movie.dao")
public class MovieApplication {
    public static void main(final String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
