package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
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

    /**
     * 插入用户与角色关系
     *
     * @param userId            用户ID
     * @param appId             应用ID
     * @param roleIdsArr        角色IDS
     * @param roleIdsDeletedArr 取消授权的角色IDS
     * @return
     */
    boolean insertUserRoleRelations(Integer userId, Integer appId, String[] roleIdsArr,
                                    String[] roleIdsDeletedArr);

    /**
     * 加载已授权给用户的角色列表
     *
     * @param userId 用户ID
     * @param appId  应用ID
     * @return
     */
    ResultEntity<List<TqAuthUserRoleRelationDO>> loadAuthorizedRoleListByAppIdAndUserId(
            Integer userId, Integer appId);
}
