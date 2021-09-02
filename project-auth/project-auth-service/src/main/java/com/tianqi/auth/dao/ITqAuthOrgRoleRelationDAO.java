package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
import com.tianqi.common.dao.IBaseDAO;

import java.util.List;

/**
 * 组织角色关联表(TqAuthOrgRoleRelation)表数据库访问层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:35
 */
public interface ITqAuthOrgRoleRelationDAO extends IBaseDAO<TqAuthOrgRoleRelationDO> {

    /**
     * 根据OrgCode查询组织角色列表
     *
     * @param orgCode
     * @return
     */
    List<String> selectOrgRoleListByOrgCode(String orgCode);
}

