package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.common.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 * 角色表(TqAuthRole)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:54
 */
public interface ITqAuthRoleService extends IBaseService<TqAuthRoleDO> {

    /**
     * 查询用户所有角色列表
     *
     * @param userId
     * @param orgId
     * @param appKey
     * @return
     */
    Set<String> selectRoleListByCondition(final Integer userId,
                                          final Integer orgId,
                                          final Integer appKey);

    /**
     * 根据角色列表获取数据权限信息
     *
     * @param roleList
     * @return
     */
    List<Integer> selectDataPermissionByRoleList(final Set<String> roleList);
}
