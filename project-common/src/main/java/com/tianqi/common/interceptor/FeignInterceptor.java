package com.tianqi.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/13 08:08
 * @Description:
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Value("${client.username}")
    private String username;
    @Value("${client.password}")
    private String password;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println(requestTemplate);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        String token = httpHeaders.getFirst("Authorization");
        requestTemplate.header("Authorization",token);
    }
}
