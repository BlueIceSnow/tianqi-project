package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthUserOrgRelationDO;
import com.tianqi.common.dao.IBaseDAO;

/**
 * 用户组织关联表(TqAuthUserOrgRelation)表数据库访问层
 *
 * @author yuantianqi
 * @since 2021-08-26 15:33:17
 */
public interface ITqAuthUserOrgRelationDAO extends IBaseDAO<TqAuthUserOrgRelationDO> {

    /**
     * 根据userId、tenantId、appKey查询用户所属组织
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     * @param appKey   应用Key
     * @return
     */
    String selectUserOrgByTenantIdAndAppKey(Integer userId, Integer tenantId,
                                            String appKey);
}

