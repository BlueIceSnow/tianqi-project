package com.tianqi.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthApplicationDAO;
import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.auth.service.ITqAuthApplicationService;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.auth.service.ITqAuthRoleService;
import com.tianqi.auth.service.ITqAuthTenantApplicationRelationService;
import com.tianqi.client.constant.AuthConstant;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 应用表(TqAuthApplication)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:25:57
 */
@Service
public class TqAuthApplicationServiceImpl
        extends BaseServiceImpl<ITqAuthApplicationDAO, TqAuthApplicationDO>
        implements ITqAuthApplicationService {
    private ITqAuthTenantApplicationRelationService tenantApplicationRelationService;
    private ITqAuthResourceService tqAuthResourceService;
    private ITqAuthRoleService roleService;

    @Autowired
    public void setTqAuthResourceService(
            final ITqAuthResourceService tqAuthResourceService) {
        this.tqAuthResourceService = tqAuthResourceService;
    }

    @Autowired
    public void setRoleService(final ITqAuthRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setTenantApplicationRelationService(
            final ITqAuthTenantApplicationRelationService tenantApplicationRelationService) {
        this.tenantApplicationRelationService = tenantApplicationRelationService;
    }

    @Override
    public ResultEntity<TqAuthApplicationDO> save(final TqAuthApplicationDO entity) {
        final ResultEntity<TqAuthApplicationDO> save = super.save(entity);
        if (save.getStatus().equals(StatusEnum.OK)) {
            // 建立其与管理租户的关系
            tenantApplicationRelationService
                    .insertApplicationTenantRelations(String.valueOf(AuthConstant.ADMIN_TENANT_ID),
                            new String[] {String.valueOf(save.getData().doOrDto().getId())},
                            new String[] {});
        }
        return save;
    }

    @Override
    public void removeRelationData(final String[] ids) {
        // 删除应用关联的资源及关系
        tqAuthResourceService
                .removeByCondition(new QueryWrapper<TqAuthResourceDO>().lambda()
                        .in(TqAuthResourceDO::getAppId, ids));
        // 删除应用关联租户的关系
        tenantApplicationRelationService
                .removeByCondition(new QueryWrapper<TqAuthTenantApplicationRelationDO>().lambda()
                        .in(TqAuthTenantApplicationRelationDO::getAppId, ids));
        // 删除该应用下的角色
        roleService.removeByCondition(
                new QueryWrapper<TqAuthRoleDO>().lambda().in(TqAuthRoleDO::getAppId, ids));
    }
}
