package com.tianqi.auth.util;

import com.tianqi.auth.config.security.authentication.JwtAuthenticationToken;
import com.tianqi.common.pojo.JwtUserClaims;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 权限操作类
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/11/02 14:03:58
 */
public class AuthUtil {

    /**
     * 获取当前登录用户角色列表
     *
     * @return
     */
    public static List<String> roles() {
        final JwtUserClaims details = userClaims();
        return details.getRoles();
    }

    /**
     * 当前登录用户AppId
     *
     * @return
     */
    public static Integer appId() {
        final JwtUserClaims detail = userClaims();
        return detail.getAppId();
    }

    /**
     * 当前登录用户TenantId
     *
     * @return
     */
    public static Integer tenantId() {
        final JwtUserClaims detail = userClaims();
        return detail.getTenantId();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static JwtUserClaims userClaims() {
        final JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        if (authentication == null) {
            return new JwtUserClaims();
        }
        return authentication.getDetails();
    }
}
