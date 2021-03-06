package com.tianqi.auth.config.security.authentication;

import com.tianqi.client.config.security.IJwtSecurityMetaService;
import com.tianqi.client.config.security.authorization.JwtAuthenticationToken;
import com.tianqi.client.pojo.JwtAuthority;
import com.tianqi.client.pojo.TqAuthUserLoginBO;
import com.tianqi.common.pojo.JwtUserClaims;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @SneakyThrows
    @Override
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {
//        Thread.sleep(600000);
//        final int i = 1 / 0;
        final JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
        final String username = jwtToken.getDetails().getUsername();
        final String credentials = String.valueOf(jwtToken.getPassword());
        final String appKey = jwtToken.getDetails().getAppKey();
        // 加载用户信息
        final TqAuthUserLoginBO
                realUserBO = securityMetaService.loadUserInfo(username, appKey);
        final List<JwtAuthority> authorities = realUserBO.getAuthorities();
        if (passwordEncoder.matches(credentials, realUserBO.getPassword())) {
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
                            realUserBO.getType(),
                            realUserBO.getState(),
                            realUserBO.getUsername(),
                            roles,
                            realUserBO.getDataPermissions(),
                            null,
                            realUserBO.getTenantId(),
                            realUserBO.getOrgCode(),
                            realUserBO.getOrgId(),
                            realUserBO.getAppKey(),
                            realUserBO.getAppId()
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
