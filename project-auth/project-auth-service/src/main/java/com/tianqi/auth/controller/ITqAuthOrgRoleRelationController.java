package com.tianqi.auth.controller;

import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
import com.tianqi.auth.pojo.dto.req.RoleOrgRelationDTO;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 组织角色关联表(TqAuthOrgRoleRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:36
 */
public interface ITqAuthOrgRoleRelationController
        extends IBaseController<TqAuthOrgRoleRelationDO> {

    /**
     * 授权角色给组织
     *
     * @param roleOrgRelationDTO
     * @return
     */
    @PutMapping("authorizeRoleToOrg")
    ResultEntity<Boolean> authorizeRoleToOrg(@RequestBody RoleOrgRelationDTO roleOrgRelationDTO);

    /**
     * 拉取授权给组织的用户列表
     *
     * @param appId
     * @param orgId
     * @return
     */
    @GetMapping("loadAuthorizedRoleList")
    ResultEntity<List<TqAuthOrgRoleRelationDO>> loadAuthorizedRoleList(Integer orgId,
                                                                       Integer appId);

}
