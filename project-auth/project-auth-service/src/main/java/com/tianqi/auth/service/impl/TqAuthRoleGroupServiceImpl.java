package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthRoleGroupDAO;
import com.tianqi.auth.pojo.TqAuthRoleGroupDO;
import com.tianqi.auth.pojo.TqAuthUserRoleGroupRelationDO;
import com.tianqi.auth.service.ITqAuthRoleGroupService;
import com.tianqi.client.util.AuthUtil;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色组表(TqAuthRoleGroup)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:09
 */
@Service
public class TqAuthRoleGroupServiceImpl
        extends BaseServiceImpl<ITqAuthRoleGroupDAO, TqAuthRoleGroupDO>
        implements ITqAuthRoleGroupService {
    private TqAuthUserRoleGroupRelationServiceImpl userRoleGroupRelationService;

    @Autowired
    public void setUserRoleGroupRelationService(
            final TqAuthUserRoleGroupRelationServiceImpl userRoleGroupRelationService) {
        this.userRoleGroupRelationService = userRoleGroupRelationService;
    }

    @Override
    public ResultEntity<TqAuthRoleGroupDO> save(final TqAuthRoleGroupDO entity) {
        if (entity.getTenantId() == null) {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.save(entity);
    }

    @Override
    public void removeRelationData(final String[] ids) {
        // 用户与角色组关联关系
        userRoleGroupRelationService.removeByCondition(
                new QueryWrapper<TqAuthUserRoleGroupRelationDO>().lambda()
                        .in(TqAuthUserRoleGroupRelationDO::getRoleGroupId, ids));
    }

    @Override
    public void removeRelationRoleField(final String[] ids) {
        final Integer[] idsInt =
                Arrays.stream(ids).map(Integer::parseInt).collect(Collectors.toList())
                        .toArray(new Integer[] {});
        final LambdaQueryWrapper<TqAuthRoleGroupDO> queryWrapper =
                new QueryWrapper<TqAuthRoleGroupDO>().lambda();
        for (int i = 0; i < idsInt.length; i++) {
            queryWrapper.apply("JSON_CONTAINS(include_roles, {0})", ids[i]);
            if (idsInt.length != i + 1) {
                queryWrapper.or();
            }
        }
        final List<TqAuthRoleGroupDO> tqAuthRoleGroupDOS = dao.selectList(queryWrapper);
        tqAuthRoleGroupDOS.forEach(item -> item.getIncludeRoles()
                .removeAll(new ArrayList<>(Arrays.asList(idsInt))));
        for (final TqAuthRoleGroupDO tqAuthRoleGroupDO : tqAuthRoleGroupDOS) {
            dao.updateById(tqAuthRoleGroupDO);
        }
    }
}
