package com.tianqi.auth.dao;

import com.alibaba.fastjson.JSONArray;
import com.tianqi.auth.pojo.TqAuthUserRoleGroupRelationDO;
import com.tianqi.common.dao.IBaseDAO;

import java.util.List;

/**
 * 用户角色组关联表(TqAuthUserRoleGroupRelation)表数据库访问层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:51
 */
public interface ITqAuthUserRoleGroupRelationDAO
        extends IBaseDAO<TqAuthUserRoleGroupRelationDO> {

    /**
     * 根据用户ID及组织Code查询用户角色
     *
     * @param id      用户ID
     * @param orgCode 组织Code
     * @return
     */
    List<JSONArray> selectUserRoleListByUserId(Integer id, String orgCode);
}

