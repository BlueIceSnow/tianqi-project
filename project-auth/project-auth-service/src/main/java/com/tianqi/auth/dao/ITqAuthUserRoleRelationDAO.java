package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.common.dao.IBaseDAO;

import java.util.List;

/**
 * 用户角色组关联表(TqAuthUserRoleRelation)表数据库访问层
 *
 * @Author yuantianqi
 * @since 2021-08-31 11:08:04
 */
public interface ITqAuthUserRoleRelationDAO
        extends IBaseDAO<TqAuthUserRoleRelationDO> {

    /**
     * 根据用户ID及应用ID获取用户角色列表
     *
     * @param userId
     * @param appId
     * @return
     */
    List<String> selectUserRoleListByUserIdAndAppId(Integer userId,
                                                    Integer appId);
}

