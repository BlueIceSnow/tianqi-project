package com.tianqi.client.config;

import com.tianqi.client.config.security.IJwtSecurityMetaService;
import com.tianqi.client.config.security.authorization.JwtConfigAttribute;
import com.tianqi.client.service.impl.AuthClientServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/22 16:09
 * @Description:
 */
@Configuration
public class DefaultConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public IJwtSecurityMetaService jwtSecurityMetaService(
            final RedisTemplate<String, List<JwtConfigAttribute>> redisTemplate) {
        return new AuthClientServiceImpl(redisTemplate);
    }
}
