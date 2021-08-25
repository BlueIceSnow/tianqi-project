package com.tianqi.auth.config.security.authentication;

import com.tianqi.auth.config.security.IJwtSecurityMetaService;
import com.tianqi.auth.config.security.authorization.JwtAuthority;
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
    @Autowired
    private IJwtSecurityMetaService securityMetaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {
        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
        final String username = jwtToken.getDetails().getUsername();
        final String credentials = String.valueOf(jwtToken.getCredentials());
        final TqUserDO realUser = securityMetaService.loadUserInfo(username);
        if (realUser != null &&
                passwordEncoder.matches(credentials, realUser.getPassword()
                )) {
            final List<JwtAuthority> jwtAuthorities =
                    securityMetaService.loadAuthorities(username);
            final JwtAuthenticationToken authenticationToken =
                    new JwtAuthenticationToken(jwtAuthorities);

            final JwtUserClaims jwtUserClaims =
                    new JwtUserClaims(realUser.getId(), realUser.getName(),
                            realUser.getUsername(),
                            jwtAuthorities.stream().map(JwtAuthority::getAuthority)
                                    .collect(Collectors.toList()),
                            UUID.randomUUID().toString());

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
