package com.tianqi.auth.service.impl;

import com.tianqi.auth.constant.AuthConstant;
import com.tianqi.auth.dao.ITqAuthRoleDAO;
import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.auth.service.ITqAuthOrgRoleRelationService;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.auth.service.ITqAuthRoleDataPermissionRelationService;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.auth.service.ITqAuthRoleService;
import com.tianqi.auth.service.ITqAuthUserRoleGroupRelationService;
import com.tianqi.auth.util.AuthUtil;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 角色表(TqAuthRole)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:54
 */
@Service
public class TqAuthRoleServiceImpl
        extends BaseServiceImpl<ITqAuthRoleDAO, TqAuthRoleDO>
        implements ITqAuthRoleService {
    private ITqAuthUserRoleGroupRelationService userRoleGroupRelationService;
    private ITqAuthOrgRoleRelationService orgRoleRelationService;
    private ITqAuthRoleDataPermissionRelationService
            roleDataPermissionRelationService;
    private ITqAuthRoleResourceRelationService roleResourceRelationService;
    private ITqAuthResourceService resourceService;

    @Autowired
    public void setResourceService(
            final ITqAuthResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Autowired
    public void setRoleResourceRelationService(
            final ITqAuthRoleResourceRelationService roleResourceRelationService) {
        this.roleResourceRelationService = roleResourceRelationService;
    }

    @Autowired
    public void setRoleDataPermissionRelationService(
            final ITqAuthRoleDataPermissionRelationService roleDataPermissionRelationService) {
        this.roleDataPermissionRelationService =
                roleDataPermissionRelationService;
    }

    @Autowired
    public void setOrgRoleRelationService(
            final ITqAuthOrgRoleRelationService orgRoleRelationService) {
        this.orgRoleRelationService = orgRoleRelationService;
    }

    @Autowired
    public void setUserRoleGroupRelationService(
            final ITqAuthUserRoleGroupRelationService userRoleGroupRelationService) {
        this.userRoleGroupRelationService = userRoleGroupRelationService;
    }

    /**
     * 查询某一用户的角色列表
     *
     * @param userId
     * @param orgId
     * @param appId
     * @return
     */
    @Override
    public Set<String> selectRoleListByCondition(final Integer userId,
                                                 final Integer orgId,
                                                 final Integer appId) {
        // 查询用户 "当前应用" 下所加入的角色组角色及直接关联角色
        final Set<String> userRoleList =
                userRoleGroupRelationService
                        .selectUserRoleListByUserIdAndAppId(userId, appId);
        if (orgId != null) {
            // 查询该应用下，用户所加入组织后拥有的角色
            final List<String> orgRoleList =
                    orgRoleRelationService.selectOrgRoleListByOrgId(orgId);
            userRoleList.addAll(orgRoleList);
        }
        return userRoleList;
    }

    /**
     * 获取数据权限列表
     *
     * @param roleList 角色列表
     * @return
     */
    @Override
    public List<Integer> selectDataPermissionByRoleList(
            final Set<String> roleList) {
        return roleDataPermissionRelationService
                .selectDataPermissionListByRoleList(roleList);
    }

    @Override
    public ResultEntity<TqAuthRoleDO> save(final TqAuthRoleDO entity) {
        if (!AuthUtil.tenantId().equals(AuthConstant.ADMIN_TENANT_ID)) {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.save(entity);
    }

    @Override
    public ResultEntity<TqAuthRoleDO> update(final TqAuthRoleDO entity) {
        if (!AuthUtil.tenantId().equals(AuthConstant.ADMIN_TENANT_ID)) {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.update(entity);
    }

    @Override
    public ResultEntity<List<TqAuthRoleDO>> listEntity(final TqAuthRoleDO entity) {
        if (AuthUtil.tenantId().equals(AuthConstant.ADMIN_TENANT_ID)) {
            entity.setTenantId(null);
        } else {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.listEntity(entity);
    }

    @Override
    public ResultEntity<List<TqAuthRoleDO>> listPageEntity(final TqAuthRoleDO entity,
                                                           final int page, final int size) {
        if (AuthUtil.tenantId().equals(AuthConstant.ADMIN_TENANT_ID)) {
            entity.setTenantId(null);
        } else {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.listPageEntity(entity, page, size);
    }
}
