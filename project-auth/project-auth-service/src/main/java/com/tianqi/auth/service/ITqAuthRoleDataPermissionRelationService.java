package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthRoleDataPermissionRelationDO;
import com.tianqi.common.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 * 角色数据权限关联表(TqAuthRoleDataPermissionRelation)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:01
 */
public interface ITqAuthRoleDataPermissionRelationService
        extends IBaseService<TqAuthRoleDataPermissionRelationDO> {


    /**
     * 根据角色列表检索数据权限列表
     *
     * @param userRoleList
     * @return
     */
    List<Integer> selectDataPermissionListByRoleList(Set<String> userRoleList);

}
