package com.tianqi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * AuthApplication主入口函数
 *
 * @author yuantianqi
 * @since 2021-04-11 17:33:57
 */
@SpringBootApplication(scanBasePackages = {"com.tianqi.common", "com.tianqi.auth"})
@MapperScan("com.tianqi.auth.dao")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}

