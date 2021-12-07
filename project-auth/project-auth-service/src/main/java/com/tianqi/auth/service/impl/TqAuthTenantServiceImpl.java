package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthTenantDAO;
import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.auth.pojo.TqAuthRoleGroupDO;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.auth.pojo.TqAuthTenantDO;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.auth.pojo.dto.req.TenantInfoDTO;
import com.tianqi.auth.service.ITqAuthTenantService;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 租户表(TqAuthTenant)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:28
 */
@Service
public class TqAuthTenantServiceImpl
        extends BaseServiceImpl<ITqAuthTenantDAO, TqAuthTenantDO>
        implements ITqAuthTenantService {

    private TqAuthUserServiceImpl userService;
    private TqAuthTenantApplicationRelationServiceImpl tenantApplicationRelationService;
    private TqAuthRoleServiceImpl roleService;
    private TqAuthRoleGroupServiceImpl roleGroupService;
    private TqAuthOrgServiceImpl orgService;

    @Autowired
    public void setTenantApplicationRelationService(
            final TqAuthTenantApplicationRelationServiceImpl tenantApplicationRelationService) {
        this.tenantApplicationRelationService = tenantApplicationRelationService;
    }

    @Autowired
    public void setRoleService(final TqAuthRoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRoleGroupService(final TqAuthRoleGroupServiceImpl roleGroupService) {
        this.roleGroupService = roleGroupService;
    }

    @Autowired
    public void setOrgService(final TqAuthOrgServiceImpl orgService) {
        this.orgService = orgService;
    }

    @Autowired
    public void setUserService(final TqAuthUserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResultEntity<TqAuthTenantDO> saveTenant(
            final TenantInfoDTO tenantInfoDTO) {
        // 首先存储租户信息
        final int insert = dao.insert(tenantInfoDTO);
        // 存储用户信息
        final TqAuthUserDO mgr = tenantInfoDTO.getMgr();
        mgr.setTenantId(tenantInfoDTO.getId());
        mgr.setType("T");
        final ResultEntity<TqAuthUserDO> save = userService.save(mgr);
        // 建立租户及用户关系
        tenantInfoDTO.setMgrId(save.getData().doOrDto().getId());

        // 将普通租户权限授予给他

        final int update = dao.updateById(tenantInfoDTO);
        final boolean isOk = save.getStatus() == StatusEnum.OK && update == 1;
        return RestResult.<com.tianqi.auth.pojo.TqAuthTenantDO>builder()
                .withStatus(isOk ? StatusEnum.OK : StatusEnum.SERVER_ERROR)
                .withData(tenantInfoDTO)
                .ok(isOk)
                .build();
    }

    @Override
    public void removeRelationData(final String[] ids) {
        // 租户与应用授权关系
        tenantApplicationRelationService
                .removeByCondition(new QueryWrapper<TqAuthTenantApplicationRelationDO>().lambda()
                        .in(TqAuthTenantApplicationRelationDO::getTenantId, ids));
        // 租户下的用户
        userService.removeByCondition(
                new QueryWrapper<TqAuthUserDO>().lambda().in(TqAuthUserDO::getTenantId, ids));
        // 租户下的角色
        roleService.removeByCondition(
                new QueryWrapper<TqAuthRoleDO>().lambda().in(TqAuthRoleDO::getTenantId, ids));
        // 租户下的角色组
        roleGroupService.removeByCondition(new QueryWrapper<TqAuthRoleGroupDO>().lambda()
                .in(TqAuthRoleGroupDO::getTenantId, ids));
        // 租户下的组织
        orgService.removeByCondition(
                new QueryWrapper<TqAuthOrgDO>().lambda().in(TqAuthOrgDO::getTenantId, ids));

    }
}
