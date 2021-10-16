package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.pojo.TqAuthUserOrgRelationDO;
import com.tianqi.common.service.IBaseService;

/**
 * 用户组织关联表(TqAuthUserOrgRelation)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-26 15:33:18
 */
public interface ITqAuthUserOrgRelationService
        extends IBaseService<TqAuthUserOrgRelationDO> {

    /**
     * 根据租户 ID 及 APP Key 查询用户所属组织
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     * @param appKey   App Key
     * @return
     */
    TqAuthOrgDO selectUserOrgByTenantIdAndAppKey(Integer userId,
                                                 Integer tenantId,
                                                 String appKey);
}
