package com.tianqi.client.config.security.authorization;

import com.tianqi.client.config.security.IJwtSecurityMetaService;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/18 08:59
 * @Description: 权限认证拦截器
 */

public class JwtSecurityInterceptorFilter extends FilterSecurityInterceptor {


    public JwtSecurityInterceptorFilter(AccessDecisionManager accessDecisionManager,
                                        SecurityMetadataSource securityMetadataSource,
                                        IJwtSecurityMetaService metaService) {
        this.setAccessDecisionManager(accessDecisionManager);
        final JwtSecurityMetaDataSource jwtSecurityMetaDataSource =
                new JwtSecurityMetaDataSource(
                        (DefaultFilterInvocationSecurityMetadataSource) securityMetadataSource);
        jwtSecurityMetaDataSource.setMetaService(metaService);
        this.setSecurityMetadataSource(jwtSecurityMetaDataSource);
    }
}
