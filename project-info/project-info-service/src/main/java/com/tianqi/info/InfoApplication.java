package com.tianqi.info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * MovieApplication主入口函数
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:17
 */
@SpringBootApplication(scanBasePackages = {"com.tianqi.common", "com.tianqi.info"})
@EnableFeignClients(basePackages = "com.tianqi.*.api")
//@EnableCircuitBreaker
@MapperScan("com.tianqi.info.dao")
public class InfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfoApplication.class, args);
    }
}
