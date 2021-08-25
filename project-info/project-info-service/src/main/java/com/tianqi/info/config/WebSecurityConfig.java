package com.tianqi.info.config;

import com.tianqi.client.annotation.TqSecurityEnable;
import com.tianqi.client.config.security.WebSecurityConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/20 21:29
 * @Description:
 */
@TqSecurityEnable
public class WebSecurityConfig extends WebSecurityConfiguration {
    /**
     * 配置权限认证相关
     *
     * @param http
     * @throws Exception
     */
    @Override

    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests();
        super.configure(http);
    }

    /**
     * 配置Web安全，会在SecurityFilterProxy中生成一个新的过滤器链
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        // 可匿名访问资源
        super.configure(web);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
