package com.tianqi.client.config.security.authorization;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/18 16:01
 * @Description: 用户权限信息
 */
public class JwtAuthority implements GrantedAuthority {

    private final String role;

    public JwtAuthority(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
