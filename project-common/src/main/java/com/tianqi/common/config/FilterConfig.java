package com.tianqi.common.config;

import com.tianqi.common.filter.RequestWrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ytq
 */
@Configuration
public class FilterConfig {
    /**
     * 注册过滤器
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<RequestWrapperFilter> someFilterRegistration() {
        final FilterRegistrationBean<RequestWrapperFilter> registration =
                new FilterRegistrationBean<>();
        registration.setFilter(requestWrapperFilter());
        registration.addUrlPatterns("/**");
        registration.setName("requestWrapperFilter");
        return registration;
    }

    /**
     * 实例化StreamFilter
     *
     * @return Filter
     */
    @Bean
    public RequestWrapperFilter requestWrapperFilter() {
        return new RequestWrapperFilter();
    }
}