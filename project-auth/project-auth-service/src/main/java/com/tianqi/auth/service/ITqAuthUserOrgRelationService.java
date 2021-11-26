package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.pojo.TqAuthUserOrgRelationDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;

import java.util.List;

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

    /**
     * 插入组织与用户关系
     *
     * @param orgId             组织ID
     * @param appId             应用ID
     * @param userIdsArr        用户IDs
     * @param userIdsArrDeleted 要删除的用户IDs
     * @return
     */
    boolean insertOrgUserRelations(Integer orgId, Integer appId, String[] userIdsArr,
                                   String[] userIdsArrDeleted);

    /**
     * 根据租户ID 应用ID及组织ID获取授权给组织的用户
     *
     * @param tenantId 租户ID
     * @param appId    应用ID
     * @param orgId    组织ID
     * @return
     */
    ResultEntity<List<TqAuthUserOrgRelationDO>> loadAuthorizedUserListByAppIdTenantIdAndOrgId(
            Integer tenantId, Integer appId, Integer orgId);
}
