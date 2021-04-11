package com.tianqi.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * MovieApplication主入口函数
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:17
 */
@SpringBootApplication(scanBasePackages = {"com.tianqi.common", "com.tianqi.movie"})
@MapperScan("com.tianqi.movie.dao")
public class MovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}

