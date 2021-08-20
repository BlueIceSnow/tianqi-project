package com.tianqi.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder
                .currentRequestAttributes())).getRequest();
        String token = "";
        if (requestTemplate.feignTarget().name().equals("project-auth-service")) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBasicAuth(username, password);
            token = httpHeaders.getFirst("Authorization");
        } else {
            token = request.getHeader("Authorization");
        }
        requestTemplate.header("Authorization", token);

    }
}