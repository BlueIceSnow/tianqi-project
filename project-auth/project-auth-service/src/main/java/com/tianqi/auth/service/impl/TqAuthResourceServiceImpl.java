package com.tianqi.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.convert.ResourceConvert;
import com.tianqi.auth.dao.ITqAuthResourceDAO;
import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.auth.service.ITqAuthRoleService;
import com.tianqi.auth.service.ITqAuthUserRoleGroupRelationService;
import com.tianqi.client.constant.AuthConstant;
import com.tianqi.client.util.AuthUtil;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.enums.database.ResourceTypeEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 资源表(TqAuthResource)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:45
 */
@Service
public class TqAuthResourceServiceImpl
        extends BaseServiceImpl<ITqAuthResourceDAO, TqAuthResourceDO>
        implements ITqAuthResourceService {

    private ResourceConvert resourceConvert;
    private ITqAuthRoleResourceRelationService roleResourceRelationService;
    private ITqAuthRoleService roleService;
    private TqAuthUserRoleRelationServiceImpl userRoleRelationService;
    private ITqAuthUserRoleGroupRelationService userRoleGroupRelationService;

    @Autowired
    public void setUserRoleRelationService(
            final TqAuthUserRoleRelationServiceImpl userRoleRelationService) {
        this.userRoleRelationService = userRoleRelationService;
    }

    @Autowired
    public void setUserRoleGroupRelationService(
            final ITqAuthUserRoleGroupRelationService userRoleGroupRelationService) {
        this.userRoleGroupRelationService = userRoleGroupRelationService;
    }

    @Autowired
    public void setRoleService(final ITqAuthRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRoleResourceRelationService(
            final ITqAuthRoleResourceRelationService roleResourceRelationService) {
        this.roleResourceRelationService = roleResourceRelationService;
    }

    @Autowired
    public void setResourceConvert(final ResourceConvert resourceConvert) {
        this.resourceConvert = resourceConvert;
    }

    /**
     * 根据应用ID及类型查询资源类表
     *
     * @param appId 应用ID
     * @param type  类型
     * @return
     */
    @Override
    public List<TqAuthResourceDO> selectResourceListByAppId(final Integer appId,
                                                            final ResourceTypeEnum type) {
        final QueryWrapper<TqAuthResourceDO> resourceQuery =
                new QueryWrapper<>();
        resourceQuery.lambda().eq(TqAuthResourceDO::getAppId, appId);
        resourceQuery.lambda().eq(TqAuthResourceDO::getType, type);
        return dao.selectList(resourceQuery);
    }

    /**
     * 根据当前登录用户信息获取用户拥有资源列表.
     *
     * @param roles
     * @param tenantId
     * @param appId
     * @return
     */
    @Override
    public List<ResourceDetailDTO> loadLoginUserResourceList(final List<String> roles,
                                                             final Integer tenantId,
                                                             final Integer appId) {
        final TqAuthResourceDO tqAuthResourceDO = new TqAuthResourceDO();
        tqAuthResourceDO.setType("M");
        final List<TqAuthResourceDO> resourceDOS =
                dao.selectMenuResource(roles, tqAuthResourceDO, tenantId,
                        appId);
        return resourceDOS.stream().map(resourceConvert::doToResourceDetailDTO)
                .collect(Collectors.toList());
    }

    /**
     * 通过角色查询菜单资源
     *
     * @param tenantId
     * @param appId
     * @return
     */
    @Override
    public List<TqAuthResourceDO> listResourceByRole(
            final TqAuthResourceDO tqAuthResourceDO,
            final Integer tenantId,
            final Integer appId) {
        // 获取当前登录用户ID
        final List<String> roles = userRoleRelationService
                .selectUserRoleListByUserIdAndAppId(AuthUtil.userClaims().getId(),
                        appId);
        final Set<String> rolesByGroup = userRoleGroupRelationService
                .selectUserRoleListByUserIdAndAppId(AuthUtil.userClaims().getId(),
                        appId);
        roles.addAll(rolesByGroup);
        return dao.selectMenuResource(roles, tqAuthResourceDO, tenantId, appId);
    }

    @Override
    public ResultEntity<TqAuthResourceDO> save(final TqAuthResourceDO entity) {
        final int i = dao.insert(entity);
        if (i != 0) {
            // 首先查询该应用下租户拥有的角色
            final List<TqAuthRoleDO> tqAuthRoleDOS =
                    roleService.listEntity(new TqAuthRoleDO().setAppId(entity.getAppId())
                            .setCode(AuthConstant.ADMIN_ROLE_CODE)
                            .setTenantId(AuthConstant.ADMIN_TENANT_ID)).getData().doOrDto();
            // 将该资源授权给管理员，管理员角色再决定授权给哪些用户
            if (CollectionUtil.isEmpty(tqAuthRoleDOS)) {
                throw new BaseException("请在该应用下创建管理账号");
            }
            final TqAuthRoleResourceRelationDO relationDO = TqAuthRoleResourceRelationDO.builder()
                    .resourceId(entity.getId())
                    .roleId(tqAuthRoleDOS.get(0).getId())
                    .tenantId(AuthConstant.ADMIN_TENANT_ID).build();
            final ResultEntity<TqAuthRoleResourceRelationDO> save =
                    roleResourceRelationService.save(relationDO);
            if (save.getStatus().equals(StatusEnum.OK)) {
                return RestResult.<TqAuthResourceDO>builder()
                        .withData(entity)
                        .ok(true)
                        .withStatus(StatusEnum.OK)
                        .build();
            }
        }
        return RestResult.<TqAuthResourceDO>builder()
                .withData(entity)
                .ok(true)
                .withStatus(StatusEnum.OPERATION_ERROR)
                .build();
    }

    @Override
    public void removeRelationData(final String[] ids) {
        // 资源与角色的关联关系
        roleResourceRelationService
                .removeByCondition(new QueryWrapper<TqAuthRoleResourceRelationDO>().lambda()
                        .in(TqAuthRoleResourceRelationDO::getResourceId, ids));

    }
}
