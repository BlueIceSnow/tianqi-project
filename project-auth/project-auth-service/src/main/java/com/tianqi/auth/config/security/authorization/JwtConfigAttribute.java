package com.tianqi.auth.config.security.authorization;

import org.springframework.security.access.ConfigAttribute;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/19 11:03
 * @Description: 用户权限元数据
 */
public class JwtConfigAttribute implements ConfigAttribute {
    public String role;

    public JwtConfigAttribute(String role) {
        this.role = role;
    }

    @Override
    public String getAttribute() {
        return this.role;
    }
}
