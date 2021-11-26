package com.tianqi.auth.controller.impl;

import cn.hutool.core.util.StrUtil;
import com.tianqi.auth.controller.ITqAuthTenantApplicationRelationController;
import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.auth.pojo.dto.req.TenantApplicationRelationDTO;
import com.tianqi.auth.service.ITqAuthTenantApplicationRelationService;
import com.tianqi.auth.util.AuthUtil;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 租户应用关联表(TqAuthTenantApplicationRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:37
 */
@RestController
@RequestMapping("tqAuthTenantApplicationRelation")
public class TqAuthTenantApplicationRelationControllerImpl extends
        BaseControllerImpl<ITqAuthTenantApplicationRelationService, TqAuthTenantApplicationRelationDO>
        implements ITqAuthTenantApplicationRelationController {

    /**
     * 加载当前登录用户拥有的应用列表
     *
     * @return 当前租户拥有的应用列表
     */
    @Override
    @GetMapping("loadApplicationListByCurrentUser")
    public ResultEntity<List<TqAuthApplicationDO>> loadApplicationListByCurrentUser() {
        final List<TqAuthApplicationDO> authApplicationDOList =
                service.loadApplicationListByTenantId(AuthUtil.tenantId());
        return RestResult.<List<TqAuthApplicationDO>>builder()
                .withStatus(StatusEnum.OK)
                .withData(authApplicationDOList)
                .ok(true).build();
    }

    @Override
    @GetMapping("loadTenantRelationApplicationList")
    public ResultEntity<List<TqAuthTenantApplicationRelationDO>> loadTenantRelationApplicationList(
            final String tenantId) {

        final List<TqAuthTenantApplicationRelationDO> result =
                service.loadTenantRelationApplicationListByTenantId(Integer.parseInt(tenantId));

        return RestResult.<List<TqAuthTenantApplicationRelationDO>>builder()
                .withStatus(StatusEnum.OK)
                .withData(result)
                .ok(true).build();
    }

    @Override
    @PutMapping("authorisedApplicationToTenant")
    public ResultEntity<Boolean> authorisedApplicationToTenant(
            @RequestBody final TenantApplicationRelationDTO tenantApplicationRelationDTO) {
        final String applicationIds = tenantApplicationRelationDTO.getApplicationIds();
        final String applicationIdsDeleted =
                tenantApplicationRelationDTO.getApplicationIdsDeleted();
        String[] applicationIdsArr = new String[] {};
        String[] applicationIdsArrDeleted = new String[] {};
        if (StrUtil.isNotBlank(applicationIds)) {
            applicationIdsArr = applicationIds.split(",");
        }
        if (StrUtil.isNotBlank(applicationIdsDeleted)) {
            applicationIdsArrDeleted = applicationIdsDeleted.split(",");
        }
        final boolean isOk =
                service.insertApplicationTenantRelations(tenantApplicationRelationDTO.getTenantId(),
                        applicationIdsArr, applicationIdsArrDeleted);
        return RestResult.<Boolean>builder()
                .withStatus(StatusEnum.OK)
                .withData(isOk)
                .ok(true).build();
    }

    @Override
    public ResultEntity<List<TqAuthApplicationDO>> loadApplicationByUsername(
            final String username) {

        return service.loadApplicationByUsername(username);
    }
}
