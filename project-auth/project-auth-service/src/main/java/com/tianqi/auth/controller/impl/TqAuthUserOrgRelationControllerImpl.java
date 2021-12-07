package com.tianqi.auth.controller.impl;

import cn.hutool.core.util.StrUtil;
import com.tianqi.auth.controller.ITqAuthUserOrgRelationController;
import com.tianqi.auth.pojo.TqAuthUserOrgRelationDO;
import com.tianqi.auth.pojo.dto.req.UserOrgRelationDTO;
import com.tianqi.auth.service.ITqAuthUserOrgRelationService;
import com.tianqi.client.util.AuthUtil;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户组织关联表(TqAuthUserOrgRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-26 15:33:19
 */
@RestController
@RequestMapping("tqAuthUserOrgRelation")
public class TqAuthUserOrgRelationControllerImpl
        extends
        BaseControllerImpl<ITqAuthUserOrgRelationService, TqAuthUserOrgRelationDO>
        implements ITqAuthUserOrgRelationController {

    @Override
    public ResultEntity<Boolean> authoriseUserToOrg(final UserOrgRelationDTO userOrgRelationDTO) {
        final String userIds = userOrgRelationDTO.getUserIds();
        final String userIdsDeleted =
                userOrgRelationDTO.getUserIdsDeleted();
        String[] userIdsArr = new String[] {};
        String[] userIdsArrDeleted = new String[] {};
        if (StrUtil.isNotBlank(userIds)) {
            userIdsArr = userIds.split(",");
        }
        if (StrUtil.isNotBlank(userIdsDeleted)) {
            userIdsArrDeleted = userIdsDeleted.split(",");
        }
        final boolean isOk =
                service.insertOrgUserRelations(userOrgRelationDTO.getOrgId(),
                        userOrgRelationDTO.getAppId(),
                        userIdsArr, userIdsArrDeleted);
        return RestResult.<Boolean>builder()
                .withStatus(StatusEnum.OK)
                .withData(isOk)
                .ok(true).build();
    }

    @Override
    public ResultEntity<List<TqAuthUserOrgRelationDO>> loadAuthorizedUserList(final Integer appId,
                                                                              final Integer orgId) {
        return service.loadAuthorizedUserListByAppIdTenantIdAndOrgId(AuthUtil.tenantId(), appId
                , orgId);
    }
}
