package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthUserRoleGroupRelationDO;
import com.tianqi.common.service.IBaseService;

import java.util.List;

/**
 * 用户角色组关联表(TqAuthUserRoleGroupRelation)表服务接口
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:51
 */
public interface ITqAuthUserRoleGroupRelationService
        extends IBaseService<TqAuthUserRoleGroupRelationDO> {

    /**
     * 根据用户 ID 及 OrgCode 查询用户角色列表
     *
     * @param userId
     * @param orgCode
     * @return
     */
    List<String> selectUserRoleListByUserId(Integer userId, String orgCode);
}
