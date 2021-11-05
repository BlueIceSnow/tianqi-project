package com.tianqi.client.util;

import com.tianqi.client.config.security.authorization.JwtAuthenticationToken;
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
     * 获取当前登录用户信息
     *
     * @return
     */
    public static JwtUserClaims userClaims() {
        final JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        return authentication.getDetails();
    }
}
