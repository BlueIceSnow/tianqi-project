package com.tianqi.auth.service.impl;

import com.tianqi.auth.constant.AuthConstant;
import com.tianqi.auth.dao.ITqAuthApplicationDAO;
import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.service.ITqAuthApplicationService;
import com.tianqi.auth.service.ITqAuthTenantApplicationRelationService;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    @Autowired
    public void setTenantApplicationRelationService(
            final ITqAuthTenantApplicationRelationService tenantApplicationRelationService) {
        this.tenantApplicationRelationService = tenantApplicationRelationService;
    }

    @Override
    public ResultEntity<TqAuthApplicationDO> save(final TqAuthApplicationDO entity) {
        final ResultEntity<TqAuthApplicationDO> save = super.save(entity);
        if (save.getStatus().equals(StatusEnum.OK)) {
            final List<String> relationApplication = tenantApplicationRelationService
                    .loadApplicationListByTenantId(AuthConstant.ADMIN_TENANT_ID).stream()
                    .map(TqAuthApplicationDO::getId).map(String::valueOf)
                    .collect(Collectors.toList());
            relationApplication.add(String.valueOf(save.getData().doOrDto().getId()));
            // 建立其与管理租户的关系
            tenantApplicationRelationService
                    .insertApplicationTenantRelations(String.valueOf(AuthConstant.ADMIN_TENANT_ID),
                            relationApplication.toArray(new String[] {}), new String[] {});
        }
        return save;
    }


}
