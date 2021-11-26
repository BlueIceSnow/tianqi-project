package com.tianqi.auth.controller;

import com.tianqi.auth.pojo.TqAuthUserOrgRelationDO;
import com.tianqi.auth.pojo.dto.req.UserOrgRelationDTO;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户组织关联表(TqAuthUserOrgRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-26 15:33:18
 */
public interface ITqAuthUserOrgRelationController
        extends IBaseController<TqAuthUserOrgRelationDO> {
    /**
     * 授权资源给角色
     *
     * @param userOrgRelationDTO 关联关系
     * @return
     */
    @PutMapping("authoriseUserToOrg")
    ResultEntity<Boolean> authoriseUserToOrg(
            @RequestBody UserOrgRelationDTO userOrgRelationDTO);

    /**
     * 拉取授权给组织的用户列表
     *
     * @param appId
     * @param orgId
     * @return
     */
    @GetMapping("loadAuthorizedUserList")
    ResultEntity<List<TqAuthUserOrgRelationDO>> loadAuthorizedUserList(Integer appId,
                                                                       Integer orgId);
}
