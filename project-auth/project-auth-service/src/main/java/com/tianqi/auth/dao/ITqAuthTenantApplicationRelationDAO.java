package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.common.dao.IBaseDAO;

import java.util.List;

/**
 * 租户应用关联表(TqAuthTenantApplicationRelation)表数据库访问层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:37
 */
public interface ITqAuthTenantApplicationRelationDAO
        extends IBaseDAO<TqAuthTenantApplicationRelationDO> {

    /**
     * 根据租户ID查询该租户拥有的应用列表
     *
     * @param tenantId 租户ID
     * @return
     */
    List<TqAuthApplicationDO> loadApplicationListByTenantId(Integer tenantId);

    /**
     * 查询已删除数据
     *
     * @param tenantId  租户ID
     * @param appId     应用ID
     * @param isDeleted 已删除
     * @return
     */
    TqAuthTenantApplicationRelationDO selectOneRelationData(Integer tenantId, Integer appId,
                                                            Integer isDeleted);


    /**
     * 更新已删除数据为未删除状态
     *
     * @param id 数据ID
     */
    void updateIsDeleteToFalseById(Integer id);
}

