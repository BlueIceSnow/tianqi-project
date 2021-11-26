package com.tianqi.auth.controller.impl;

import cn.hutool.core.util.StrUtil;
import com.tianqi.auth.controller.ITqAuthOrgRoleRelationController;
import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
import com.tianqi.auth.pojo.dto.req.RoleOrgRelationDTO;
import com.tianqi.auth.service.ITqAuthOrgRoleRelationService;
import com.tianqi.auth.util.AuthUtil;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 组织角色关联表(TqAuthOrgRoleRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:36
 */
@RestController
@RequestMapping("tqAuthOrgRoleRelation")
public class TqAuthOrgRoleRelationControllerImpl
        extends
        BaseControllerImpl<ITqAuthOrgRoleRelationService, TqAuthOrgRoleRelationDO>
        implements ITqAuthOrgRoleRelationController {

    @Override
    public ResultEntity<Boolean> authorizeRoleToOrg(final RoleOrgRelationDTO roleOrgRelationDTO) {
        final String roleIds = roleOrgRelationDTO.getRoleIds();
        final String roleIdsDeleted =
                roleOrgRelationDTO.getRoleIdsDeleted();
        String[] roleIdsArr = new String[] {};
        String[] roleIdsDeletedArr = new String[] {};
        if (StrUtil.isNotBlank(roleIds)) {
            roleIdsArr = roleIds.split(",");
        }
        if (StrUtil.isNotBlank(roleIdsDeleted)) {
            roleIdsDeletedArr = roleIdsDeleted.split(",");
        }
        final boolean isOk =
                service.insertOrgRoleRelations(roleOrgRelationDTO.getOrgId(),
                        roleOrgRelationDTO.getAppId(),
                        roleIdsArr, roleIdsDeletedArr);
        return RestResult.<Boolean>builder()
                .withStatus(StatusEnum.OK)
                .withData(isOk)
                .ok(true).build();
    }

    @Override
    public ResultEntity<List<TqAuthOrgRoleRelationDO>> loadAuthorizedRoleList(final Integer orgId,
                                                                              final Integer appId) {
        return service.loadAuthorizedRoleListByAppIdTenantIdAndOrgId(AuthUtil.tenantId(), appId
                , orgId);
    }
}
