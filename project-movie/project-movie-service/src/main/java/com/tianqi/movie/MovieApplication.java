package com.tianqi.movie;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Map;

/**
 * MovieApplication主入口函数
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:17
 */
@SpringBootApplication(scanBasePackages = {"com.tianqi.common", "com.tianqi.movie"})
@MapperScan("com.tianqi.movie.dao")
@EnableFeignClients(basePackages = "com.tianqi.*.api")
//@EnableCircuitBreaker
public class MovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}

