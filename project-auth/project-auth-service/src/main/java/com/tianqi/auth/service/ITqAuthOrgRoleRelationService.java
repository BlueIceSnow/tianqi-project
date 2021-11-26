package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;

import java.util.List;

/**
 * 组织角色关联表(TqAuthOrgRoleRelation)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:36
 */
public interface ITqAuthOrgRoleRelationService
        extends IBaseService<TqAuthOrgRoleRelationDO> {

    /**
     * 根据OrgCode查询组织所拥有的角色
     *
     * @param orgId 组织ID
     * @return
     */
    List<String> selectOrgRoleListByOrgId(final Integer orgId);

    /**
     * 简历组织与角色的关联关系
     *
     * @param orgId             组织ID
     * @param appId             应用ID
     * @param roleIdsArr        授权的角色ID列表
     * @param roleIdsDeletedArr 取消授权的角色ID列表
     * @return
     */
    boolean insertOrgRoleRelations(Integer orgId, Integer appId, String[] roleIdsArr,
                                   String[] roleIdsDeletedArr);

    /**
     * 获取授权给组织的角色列表
     *
     * @param tenantId 租户ID
     * @param appId    应用ID
     * @param orgId    组织ID
     * @return
     */
    ResultEntity<List<TqAuthOrgRoleRelationDO>> loadAuthorizedRoleListByAppIdTenantIdAndOrgId(
            Integer tenantId, Integer appId, Integer orgId);
}
