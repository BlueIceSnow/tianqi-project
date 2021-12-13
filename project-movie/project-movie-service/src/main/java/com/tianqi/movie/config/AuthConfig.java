package com.tianqi.movie.config;

import com.tianqi.client.config.security.IJwtSecurityMetaService;
import com.tianqi.client.config.security.authorization.JwtConfigAttribute;
import com.tianqi.client.service.impl.AuthClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/12/10 11:23:07
 */
@Configuration
public class AuthConfig {
    @Bean
    public IJwtSecurityMetaService jwtSecurityMetaService(
            final RedisTemplate<String, List<JwtConfigAttribute>> redisTemplate) {
        return new AuthClientServiceImpl(redisTemplate) {
            @Override
            public List<String> loadIgnoringAuthorities() {
                final List<String> result = new ArrayList<>();
                result.add("/user/listAll");
                return result;
            }
        };
    }
}
