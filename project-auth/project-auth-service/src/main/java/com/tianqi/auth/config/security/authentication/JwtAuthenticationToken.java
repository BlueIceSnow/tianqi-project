package com.tianqi.auth.config.security.authentication;

import com.tianqi.auth.pojo.TqUserDO;
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
    public TqUserDO getDetails() {
        return (TqUserDO) super.getDetails();
    }

    @Override
    public Object getCredentials() {
        return getDetails().getPassword();
    }

    @Override
    public Object getPrincipal() {
        return getDetails().getUsername();
    }
}
