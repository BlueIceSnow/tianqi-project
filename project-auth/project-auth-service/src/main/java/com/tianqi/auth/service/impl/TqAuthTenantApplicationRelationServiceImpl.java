package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthTenantApplicationRelationDAO;
import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.auth.service.ITqAuthTenantApplicationRelationService;
import com.tianqi.auth.service.ITqAuthUserService;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 租户应用关联表(TqAuthTenantApplicationRelation)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:37
 */
@Service
public class TqAuthTenantApplicationRelationServiceImpl extends
        BaseServiceImpl<ITqAuthTenantApplicationRelationDAO, TqAuthTenantApplicationRelationDO>
        implements ITqAuthTenantApplicationRelationService {
    private ITqAuthUserService userService;

    @Autowired
    public void setUserService(final ITqAuthUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<TqAuthApplicationDO> loadApplicationListByTenantId(final Integer tenantId) {
        return dao.loadApplicationListByTenantId(tenantId);
    }

    @Override
    public List<TqAuthTenantApplicationRelationDO> loadTenantRelationApplicationListByTenantId(
            final Integer tenantId) {
        final QueryWrapper<TqAuthTenantApplicationRelationDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TqAuthTenantApplicationRelationDO::getTenantId, tenantId);
        return dao.selectList(queryWrapper);
    }

    @Override
    public boolean insertApplicationTenantRelations(final String tenantId,
                                                    final String[] applicationIdsArr,
                                                    final String[] applicationIdsArrDeleted) {
        int insert = 0;
        int deleted = 0;
        for (final String applicationId : applicationIdsArr) {
            final TqAuthTenantApplicationRelationDO tqAuthRoleResourceRelationDO =
                    new TqAuthTenantApplicationRelationDO(Integer.parseInt(tenantId),
                            Integer.parseInt(applicationId));
            insert = dao.insert(tqAuthRoleResourceRelationDO);
        }
        if (applicationIdsArrDeleted.length != 0) {
            deleted = dao.delete(new QueryWrapper<TqAuthTenantApplicationRelationDO>().lambda()
                    .eq(TqAuthTenantApplicationRelationDO::getTenantId, tenantId)
                    .in(TqAuthTenantApplicationRelationDO::getAppId, applicationIdsArrDeleted));
        }

        return deleted == applicationIdsArrDeleted.length && insert == applicationIdsArr.length;
    }

    @Override
    public ResultEntity<List<TqAuthApplicationDO>> loadApplicationByUsername(
            final String username) {
        List<TqAuthApplicationDO> applicationDOS = new ArrayList<>();
        // 根据用名查询其所属租户
        final ResultEntity<List<TqAuthUserDO>> listResultEntity =
                userService.listEntity(new TqAuthUserDO().setUsername(username));
        if (listResultEntity.getData().doOrDto().size() != 0) {
            final Integer tenantId = listResultEntity.getData().doOrDto().get(0).getTenantId();
            applicationDOS = loadApplicationListByTenantId(tenantId);
        }
        return RestResult.<List<TqAuthApplicationDO>>builder().withData(applicationDOS)
                .withStatus(StatusEnum.OK).build();
    }
}
