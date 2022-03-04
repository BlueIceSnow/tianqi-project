package com.tianqi.client.util;

import cn.hutool.core.collection.CollectionUtil;
import com.tianqi.client.config.security.authorization.JwtAuthenticationToken;
import com.tianqi.client.config.security.authorization.JwtConfigAttribute;
import com.tianqi.client.constant.AuthConstant;
import com.tianqi.common.constant.SystemConstant;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.util.SpringUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
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

    /**
     * 判断当前登录是否为管理租户
     *
     * @return
     */
    public static boolean isAdmin() {
        return AuthConstant.ADMIN_TENANT_ID.equals(tenantId());
    }

    /**
     * 失效权限缓存
     *
     * @param tenantId 租户ID
     * @param appId    应用ID
     */
    public static void invalidateAuthorityCache(final Integer tenantId, final Integer appId) {
        final RedisTemplate<String, List<JwtConfigAttribute>> redisTemplate =
                SpringUtil.getBean(
                        new ParameterizedTypeReference<RedisTemplate<String, List<JwtConfigAttribute>>>() {
                        });
        final String key = SystemConstant.REDIS_PREFIX + SystemConstant.REDIS_AUTH_PREFIX +
                SystemConstant.REDIS_TENANT_PREFIX + tenantId +
                SystemConstant.REDIS_APP_PREFIX + appId;
        if (CollectionUtil.isNotEmpty(redisTemplate.opsForHash().values(key))) {
            redisTemplate.opsForHash()
                    .delete(key, redisTemplate.opsForHash().keys(key).toArray(new String[] {}));
        }
    }
}
