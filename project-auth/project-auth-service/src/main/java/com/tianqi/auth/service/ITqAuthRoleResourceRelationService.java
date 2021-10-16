package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 角色资源关联表(TqAuthRoleResourceRelation)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:18
 */
public interface ITqAuthRoleResourceRelationService
        extends IBaseService<TqAuthRoleResourceRelationDO> {
    /**
     * 根据角色列表，检索与其对应的资源
     *
     * @param tenantId 租户ID
     * @param appId    应用ID
     * @return
     */
    Map<String, List<String>> selectResourceRoleMapping(final Integer tenantId,
                                                        final Integer appId);
}
