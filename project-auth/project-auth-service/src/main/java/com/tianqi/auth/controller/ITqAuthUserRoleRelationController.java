package com.tianqi.auth.controller;

import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.auth.pojo.dto.req.UserRoleRelationDTO;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户角色组关联表(TqAuthUserRoleRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-31 11:08:05
 */
public interface ITqAuthUserRoleRelationController
        extends IBaseController<TqAuthUserRoleRelationDO> {
    /**
     * 授权角色给用户
     *
     * @param userRoleRelationDTO
     * @return
     */
    @PutMapping("authorizeRoleToUser")
    ResultEntity<Boolean> authorizeRoleToUser(@RequestBody UserRoleRelationDTO userRoleRelationDTO);

    /**
     * 拉取授权给用户的角色列表
     *
     * @param appId
     * @param userId
     * @return
     */
    @GetMapping("loadAuthorizedRoleList")
    ResultEntity<List<TqAuthUserRoleRelationDO>> loadAuthorizedRoleList(Integer userId,
                                                                        Integer appId);
}
