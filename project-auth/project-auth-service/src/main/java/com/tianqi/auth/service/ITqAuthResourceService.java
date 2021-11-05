package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO;
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
     * 获取登录用户信息及其拥有的资源列表
     *
     * @param roles
     * @param tenantId
     * @param appId
     * @return
     */
    List<ResourceDetailDTO> loadLoginUserResourceList(
            final List<String> roles, Integer tenantId, final Integer appId);

    /**
     * 根据角色获取菜单列表
     *
     * @param roles
     * @param tqAuthResourceDO
     * @param tenantId
     * @param appId
     * @return
     */
    List<TqAuthResourceDO> listResourceByRole(final List<String> roles,
                                              final TqAuthResourceDO tqAuthResourceDO,
                                              final Integer tenantId,
                                              final Integer appId);
}
