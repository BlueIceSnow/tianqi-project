package com.tianqi.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthRoleDAO;
import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.auth.service.ITqAuthOrgRoleRelationService;
import com.tianqi.auth.service.ITqAuthRoleDataPermissionRelationService;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.auth.service.ITqAuthRoleService;
import com.tianqi.auth.service.ITqAuthUserRoleGroupRelationService;
import com.tianqi.auth.service.ITqAuthUserRoleRelationService;
import com.tianqi.client.util.AuthUtil;
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
    private ITqAuthUserRoleRelationService userRoleRelationService;
    private TqAuthRoleGroupServiceImpl roleGroupService;

    @Autowired
    public void setRoleGroupService(final TqAuthRoleGroupServiceImpl roleGroupService) {
        this.roleGroupService = roleGroupService;
    }

    @Autowired
    public void setUserRoleRelationService(
            final ITqAuthUserRoleRelationService userRoleRelationService) {
        this.userRoleRelationService = userRoleRelationService;
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
        if (!AuthUtil.isAdmin()) {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.save(entity);
    }

    @Override
    public ResultEntity<TqAuthRoleDO> update(final TqAuthRoleDO entity) {
        if (!AuthUtil.isAdmin()) {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.update(entity);
    }

    @Override
    public ResultEntity<List<TqAuthRoleDO>> listEntity(final TqAuthRoleDO entity) {
        if (!AuthUtil.isAdmin()) {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.listEntity(entity);
    }

    @Override
    public ResultEntity<List<TqAuthRoleDO>> listPageEntity(final TqAuthRoleDO entity,
                                                           final int page, final int size) {
        if (!AuthUtil.isAdmin()) {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.listPageEntity(entity, page, size);
    }

    @Override
    public void removeRelationData(final String[] ids) {
        // 角色与资源
        roleResourceRelationService.removeByCondition(
                new QueryWrapper<TqAuthRoleResourceRelationDO>().lambda()
                        .in(TqAuthRoleResourceRelationDO::getRoleId, ids));
        // 角色与用户
        userRoleRelationService.removeByCondition(new QueryWrapper<TqAuthUserRoleRelationDO>()
                .lambda().in(TqAuthUserRoleRelationDO::getRoleId, ids));
        // 角色与组织
        orgRoleRelationService
                .removeByCondition(new QueryWrapper<TqAuthOrgRoleRelationDO>().lambda()
                        .in(TqAuthOrgRoleRelationDO::getRoleId, ids));
        // 角色与角色组
        roleGroupService.removeRelationRoleField(ids);
    }

    @Override
    public ResultEntity<List<TqAuthRoleDO>> remove(final TqAuthRoleDO entity, final String id) {
        invalidateAuthorityCacheByRoleId(id);
        return super.remove(entity, id);
    }

    @Override
    public ResultEntity<List<TqAuthRoleDO>> removeByPage(final TqAuthRoleDO entity, final int page,
                                                         final int size, final String id) {
        invalidateAuthorityCacheByRoleId(id);
        return super.removeByPage(entity, page, size, id);
    }

    /**
     * 失效缓存，通过角色ID
     *
     * @param id
     */
    private void invalidateAuthorityCacheByRoleId(final String id) {
        final TqAuthRoleDO tqAuthRoleDO = dao.selectById(id);
        if (tqAuthRoleDO != null) {
            AuthUtil.invalidateAuthorityCache(tqAuthRoleDO.getTenantId(), tqAuthRoleDO.getAppId());
        }
    }
}
