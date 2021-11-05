package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.constant.AuthConstant;
import com.tianqi.auth.convert.ResourceConvert;
import com.tianqi.auth.dao.ITqAuthResourceDAO;
import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.auth.util.AuthUtil;
import com.tianqi.common.enums.ResourceTypeEnum;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
     * @param roles
     * @param tenantId
     * @param appId
     * @return
     */
    @Override
    public List<TqAuthResourceDO> listResourceByRole(final List<String> roles,
                                                     final TqAuthResourceDO tqAuthResourceDO,
                                                     final Integer tenantId,
                                                     final Integer appId) {
        return dao.selectMenuResource(roles, tqAuthResourceDO, tenantId, appId);
    }

    /**
     * 批量删除
     *
     * @param entity
     * @param ids
     * @return
     */
    @Override
    public ResultEntity<List<TqAuthResourceDO>> batchRemove(final TqAuthResourceDO entity,
                                                            final String ids) {
        final int i = dao.deleteBatchIds(Arrays.asList(ids.split(",")));
        if (i != 0) {
            return removeOk(entity);
        }
        return RestResult.<List<TqAuthResourceDO>>builder()
                .withStatus(StatusEnum.DELETE_ERROR)
                .ok(false)
                .withData(new ArrayList<>())
                .build();
    }

    @Override
    public ResultEntity<List<TqAuthResourceDO>> remove(final TqAuthResourceDO entity,
                                                       final String id) {
        final int i = dao.deleteById(id);
        if (i != 0) {
            return removeOk(entity);
        }
        return RestResult.<List<TqAuthResourceDO>>builder()
                .withStatus(StatusEnum.DELETE_ERROR)
                .ok(false)
                .withData(new ArrayList<>())
                .build();
    }

    /**
     * 删除数据成功后核心代码
     *
     * @param entity 查询条件
     * @return 删除数据后的数据列表
     */
    @Override
    protected ResultEntity<List<TqAuthResourceDO>> removeOk(final TqAuthResourceDO entity) {
        final List<TqAuthResourceDO> listRestResult =
                listResourceByRole(AuthUtil.roles(), entity, AuthUtil.tenantId(),
                        AuthUtil.appId());
        return RestResult.<List<TqAuthResourceDO>>builder()
                .withStatus(StatusEnum.OK)
                .ok(true)
                .withData(listRestResult)
                .build();
    }

    @Override
    public ResultEntity<TqAuthResourceDO> save(final TqAuthResourceDO entity) {
        final int i = dao.insert(entity);
        if (i != 0) {
            // 将该资源授权给管理员，管理员角色再决定授权给哪些用户
            final TqAuthRoleResourceRelationDO relationDO = TqAuthRoleResourceRelationDO.builder()
                    .resourceId(entity.getId())
                    .roleId(AuthConstant.ADMIN_ROLE_ID)
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
}
