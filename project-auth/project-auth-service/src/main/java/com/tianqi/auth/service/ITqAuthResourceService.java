package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.vo.UserLoginInfoVO;
import com.tianqi.common.enums.ResourceTypeEnum;
import com.tianqi.common.service.IBaseService;

import java.util.List;

/**
 * 资源表(TqAuthResource)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:45
 */
public interface ITqAuthResourceService extends IBaseService<TqAuthResourceDO> {

    /**
     * 根据AppId及类型检索所有的资源
     *
     * @param appId 应用ID
     * @param type  类型
     * @return
     */
    List<TqAuthResourceDO> selectResourceListByAppId(Integer appId,
                                                     ResourceTypeEnum type);

    /**
     * 获取当前登录用户的资源列表
     *
     * @param roles
     * @param appId
     * @param tenantId
     * @return: java.util.List<com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO>
     * @date: 2021/10/19 19:55:34
     */
    UserLoginInfoVO loadLoginUserInfoAndResourceList(
            final List<String> roles, Integer tenantId, final Integer appId);

}
