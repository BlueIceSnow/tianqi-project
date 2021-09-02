package com.tianqi.client.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.tianqi.client.config.interceptor.TenantInterceptor;
import com.tianqi.client.config.security.IJwtSecurityMetaService;
import com.tianqi.client.service.impl.AuthClientServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/22 16:09
 * @Description:
 */
@Configuration
public class DefaultConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public IJwtSecurityMetaService jwtSecurityMetaService() {
        return new AuthClientServiceImpl();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        final MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantInterceptor());
        return interceptor;
    }
}
