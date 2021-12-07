package com.tianqi.client.config.security;

import com.tianqi.client.pojo.TqAuthUserLoginBO;
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
     * 加载用户信息
     *
     * @param username 用户唯一标识
     * @param appKey   应用key
     * @return
     */
    default TqAuthUserLoginBO loadUserInfo(final String username, final String appKey) {
        return null;
    }

    ;

    /**
     * 无效校验权限的路径
     *
     * @return
     */
    List<String> loadIgnoringAuthorities();


    /**
     * 加载权限元数据
     *
     * @return
     */
    Map<String, List<ConfigAttribute>> loadMetaAuthorities();

}
