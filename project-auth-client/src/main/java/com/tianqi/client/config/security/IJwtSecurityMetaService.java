package com.tianqi.client.config.security;

import org.springframework.security.access.ConfigAttribute;

import java.util.List;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/19 15:01
 * @Description: 获取用户信息及权限信息接口
 */
public interface IJwtSecurityMetaService {
    /**
     * 无效校验权限的路径
     *
     * @return 返回不进行权限拦截的资源
     */
    List<String> loadIgnoringAuthorities();

    /**
     * 加载权限元数据
     *
     * @return 加载该系统的静态资源
     */
    Map<String, List<ConfigAttribute>> loadMetaAuthorities();

}
