package com.tianqi.client.config.security.authorization;

import com.tianqi.common.pojo.JwtUserClaims;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/15 10:46
 * @Description: 封装用户登录TOKEN
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {


    public JwtAuthenticationToken(
            final Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public JwtUserClaims getDetails() {
        return (JwtUserClaims) super.getDetails();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return getDetails().getUsername();
    }
}
