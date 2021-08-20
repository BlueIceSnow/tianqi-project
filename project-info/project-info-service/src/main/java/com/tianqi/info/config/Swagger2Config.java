package com.tianqi.info.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/11 17:50
 * @Description:
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket getUserDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("MovieApplication")
                .description("MovieApplication接口")
                .version("1.0.0")
                .contact(new Contact("yuantianqi", "http://xtianqix.gitee.io", "1820846241@qq.com"))
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tianqi.movie.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

