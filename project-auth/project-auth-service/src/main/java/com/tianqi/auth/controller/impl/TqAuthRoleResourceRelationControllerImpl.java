package com.tianqi.auth.controller.impl;

import cn.hutool.core.util.StrUtil;
import com.tianqi.auth.controller.ITqAuthRoleResourceRelationController;
import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.pojo.dto.req.RoleResourceRelationDTO;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色资源关联表(TqAuthRoleResourceRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:18
 */
@RestController
@RequestMapping("tqAuthRoleResourceRelation")
public class TqAuthRoleResourceRelationControllerImpl extends
        BaseControllerImpl<ITqAuthRoleResourceRelationService, TqAuthRoleResourceRelationDO>
        implements ITqAuthRoleResourceRelationController {

    @Override
    public ResultEntity<List<TqAuthRoleResourceRelationDO>> loadAuthorisedResByRoleId(
            final String roleId,
            final String type) {
        return service.loadAuthorisedResByRoleIdAndType(roleId, type);
    }

    @Override
    public ResultEntity<Boolean> authoriseResToRole(
            final RoleResourceRelationDTO roleResourceRelationDTO) {
        final String resIds = roleResourceRelationDTO.getResIds();
        String[] resIdsArr = new String[] {};
        String[] resIdsArrDeleted = new String[] {};
        if (StrUtil.isNotBlank(resIds)) {
            resIdsArr = resIds.split(",");
        }
        if (StrUtil.isNotBlank(roleResourceRelationDTO.getResIdsDeleted())) {
            resIdsArrDeleted = roleResourceRelationDTO.getResIdsDeleted().split(",");
        }
        final boolean isOk =
                service.insertRoleResourceRelations(roleResourceRelationDTO.getTenantId(),
                        roleResourceRelationDTO.getRoleId(), roleResourceRelationDTO.getAppId(),
                        resIdsArr, resIdsArrDeleted);
        return RestResult.<Boolean>builder()
                .withStatus(StatusEnum.OK)
                .withData(isOk)
                .ok(true).build();
    }
}
