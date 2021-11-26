package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;

import java.util.List;

/**
 * 租户应用关联表(TqAuthTenantApplicationRelation)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:37
 */
public interface ITqAuthTenantApplicationRelationService
        extends IBaseService<TqAuthTenantApplicationRelationDO> {

    /**
     * 根据租户ID获取应用列表
     *
     * @param tenantId 租户ID
     * @return
     */
    List<TqAuthApplicationDO> loadApplicationListByTenantId(Integer tenantId);

    /**
     * 根据租户ID查询该租户下从属的应用
     *
     * @param tenantId 租户ID
     * @return
     */
    List<TqAuthTenantApplicationRelationDO> loadTenantRelationApplicationListByTenantId(
            Integer tenantId);

    /**
     * 为租户批量插入授权给其的应用
     *
     * @param tenantId                 租户ID
     * @param applicationIdsArr        应用IDS数组
     * @param applicationIdsArrDeleted 删除的关系
     * @return
     */
    boolean insertApplicationTenantRelations(String tenantId, String[] applicationIdsArr,
                                             String[] applicationIdsArrDeleted);

    /**
     * 根据用户名加载应用列表
     *
     * @param username 用户名
     * @return 应用列表
     */
    ResultEntity<List<TqAuthApplicationDO>> loadApplicationByUsername(String username);

}
