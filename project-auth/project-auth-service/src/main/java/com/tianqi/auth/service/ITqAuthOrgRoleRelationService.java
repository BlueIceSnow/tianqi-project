package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
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
}
