package com.tianqi.client.interceptor;

import com.tianqi.client.constant.SystemConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
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
        final String authorization = request.getHeader(SystemConstant.HEADER_TOKEN);
        requestTemplate.header(SystemConstant.HEADER_TOKEN, authorization);
    }
}