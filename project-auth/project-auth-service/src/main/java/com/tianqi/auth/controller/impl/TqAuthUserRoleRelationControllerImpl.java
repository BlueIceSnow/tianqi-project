package com.tianqi.auth.controller.impl;

import cn.hutool.core.util.StrUtil;
import com.tianqi.auth.controller.ITqAuthUserRoleRelationController;
import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.auth.pojo.dto.req.UserRoleRelationDTO;
import com.tianqi.auth.service.ITqAuthUserRoleRelationService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户角色组关联表(TqAuthUserRoleRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-31 11:08:05
 */
@RestController
@RequestMapping("tqAuthUserRoleRelation")
public class TqAuthUserRoleRelationControllerImpl extends
        BaseControllerImpl<ITqAuthUserRoleRelationService, TqAuthUserRoleRelationDO>
        implements ITqAuthUserRoleRelationController {
    @Override
    public ResultEntity<Boolean> authorizeRoleToUser(
            final UserRoleRelationDTO userRoleRelationDTO) {
        final String roleIds = userRoleRelationDTO.getRoleIds();
        final String roleIdsDeleted =
                userRoleRelationDTO.getRoleIdsDeleted();
        String[] roleIdsArr = new String[] {};
        String[] roleIdsDeletedArr = new String[] {};
        if (StrUtil.isNotBlank(roleIds)) {
            roleIdsArr = roleIds.split(",");
        }
        if (StrUtil.isNotBlank(roleIdsDeleted)) {
            roleIdsDeletedArr = roleIdsDeleted.split(",");
        }
        final boolean isOk =
                service.insertUserRoleRelations(userRoleRelationDTO.getUserId(),
                        userRoleRelationDTO.getAppId(),
                        roleIdsArr, roleIdsDeletedArr);
        return RestResult.<Boolean>builder()
                .withStatus(StatusEnum.OK)
                .withData(isOk)
                .ok(true).build();
    }

    @Override
    public ResultEntity<List<TqAuthUserRoleRelationDO>> loadAuthorizedRoleList(final Integer userId,
                                                                               final Integer appId) {
        return service.loadAuthorizedRoleListByAppIdAndUserId(userId, appId);
    }
}
