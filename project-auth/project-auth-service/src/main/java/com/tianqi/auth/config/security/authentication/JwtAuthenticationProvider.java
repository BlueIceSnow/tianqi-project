package com.tianqi.auth.config.security.authentication;

import com.tianqi.auth.config.security.IJwtSecurityMetaService;
import com.tianqi.auth.pojo.bo.TqAuthUserLoginBO;
import com.tianqi.auth.pojo.entity.JwtAuthority;
import com.tianqi.common.pojo.JwtUserClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/17 15:18
 * @Description: 封装具体的认证逻辑
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private IJwtSecurityMetaService securityMetaService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setSecurityMetaService(
            final IJwtSecurityMetaService securityMetaService) {
        this.securityMetaService = securityMetaService;
    }

    @Autowired
    public void setPasswordEncoder(
            final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {
        final JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
        final String username = jwtToken.getDetails().getUsername();
        final String credentials = String.valueOf(jwtToken.getCredentials());
        final String appKey = jwtToken.getDetails().getAppKey();
        final Integer tenantId = jwtToken.getDetails().getTenantId();
        // 加载用户信息
        final TqAuthUserLoginBO
                realUserBO = securityMetaService.loadUserInfo(username, tenantId, appKey);
        final List<JwtAuthority> authorities = realUserBO.getAuthorities();
        if (passwordEncoder.matches(credentials, realUserBO.getPassword()
        )) {
            final JwtAuthenticationToken authenticationToken =
                    new JwtAuthenticationToken(authorities);
            // 将用户权限转为字符串列表
            final List<String> roles =
                    authorities.stream().map(JwtAuthority::getAuthority)
                            .collect(Collectors.toList());
            // 构建已登录用户
            final JwtUserClaims jwtUserClaims =
                    new JwtUserClaims(realUserBO.getUserId(),
                            realUserBO.getName(),
                            realUserBO.getUsername(),
                            roles,
                            UUID.randomUUID().toString(),
                            realUserBO.getTenantId(),
                            realUserBO.getOrgCode(),
                            realUserBO.getAppKey()
                    );
            authenticationToken.setDetails(jwtUserClaims);
            authenticationToken.setAuthenticated(true);
            return authenticationToken;
        }
        throw new UsernameNotFoundException("username not fund exception!");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
