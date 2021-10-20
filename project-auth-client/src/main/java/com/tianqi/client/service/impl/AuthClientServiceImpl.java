package com.tianqi.client.service.impl;

import com.tianqi.client.config.security.authorization.JwtAuthenticationToken;
import com.tianqi.client.config.security.authorization.JwtConfigAttribute;
import com.tianqi.client.service.IAuthClientService;
import com.tianqi.common.constant.SystemConstant;
import com.tianqi.common.pojo.JwtUserClaims;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/22 10:58
 * @Description:
 */
public class AuthClientServiceImpl implements IAuthClientService {
    private final RedisTemplate<String, List<JwtConfigAttribute>> redisTemplate;

    public AuthClientServiceImpl(
            final RedisTemplate<String, List<JwtConfigAttribute>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<String> loadIgnoringAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, List<ConfigAttribute>> loadMetaAuthorities() {
        final JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        final JwtUserClaims details = authentication.getDetails();
        return redisTemplate.<String, List<ConfigAttribute>>opsForHash()
                .entries(SystemConstant.REDIS_PREFIX + SystemConstant.REDIS_AUTH_PREFIX +
                        SystemConstant.REDIS_TENANT_PREFIX + details.getTenantId() +
                        SystemConstant.REDIS_APP_PREFIX + details.getAppId());
    }
}
