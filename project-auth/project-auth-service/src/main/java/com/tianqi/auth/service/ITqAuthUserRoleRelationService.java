package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.common.service.IBaseService;

import java.util.List;

/**
 * 用户角色组关联表(TqAuthUserRoleRelation)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-31 11:08:04
 */
public interface ITqAuthUserRoleRelationService
        extends IBaseService<TqAuthUserRoleRelationDO> {

    /**
     * 根据用户ID及角色ID获取用户所拥有角色
     *
     * @param userId
     * @param appId
     * @return
     */
    List<String> selectUserRoleListByUserIdAndAppId(Integer userId,
                                                    Integer appId);
}
