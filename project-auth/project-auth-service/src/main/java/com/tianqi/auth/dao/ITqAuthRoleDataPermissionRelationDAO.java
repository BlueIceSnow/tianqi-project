package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthRoleDataPermissionRelationDO;
import com.tianqi.common.dao.IBaseDAO;

import java.util.List;
import java.util.Set;

/**
 * 角色数据权限关联表(TqAuthRoleDataPermissionRelation)表数据库访问层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:01
 */
public interface ITqAuthRoleDataPermissionRelationDAO
        extends IBaseDAO<TqAuthRoleDataPermissionRelationDO> {

    /**
     * 根据角色列表，检索各个角色关联的数据权限IDs
     *
     * @param userRoleList
     * @return
     */
    List<Integer> selectDataPermissionListByRoleList(Set<String> userRoleList);
}

