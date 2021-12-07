package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthUserOrgRelationDAO;
import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.pojo.TqAuthUserOrgRelationDO;
import com.tianqi.auth.service.ITqAuthUserOrgRelationService;
import com.tianqi.client.util.AuthUtil;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户组织关联表(TqAuthUserOrgRelation)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-26 15:33:18
 */
@Service
public class TqAuthUserOrgRelationServiceImpl
        extends
        BaseServiceImpl<ITqAuthUserOrgRelationDAO, TqAuthUserOrgRelationDO>
        implements ITqAuthUserOrgRelationService {

    private ITqAuthUserOrgRelationDAO userOrgRelationDAO;

    @Autowired
    public void setUserOrgRelationDAO(
            final ITqAuthUserOrgRelationDAO userOrgRelationDAO) {
        this.userOrgRelationDAO = userOrgRelationDAO;
    }

    @Override
    public TqAuthOrgDO selectUserOrgByTenantIdAndAppKey(final Integer userId,
                                                        final Integer tenantId,
                                                        final String appKey) {
        return userOrgRelationDAO
                .selectUserOrgByTenantIdAndAppKey(userId, tenantId, appKey);
    }

    @Override
    public boolean insertOrgUserRelations(final Integer orgId, final Integer appId,
                                          final String[] userIdsArr,
                                          final String[] userIdsArrDeleted) {
        int insert = 0;
        int deleted = 0;
        for (final String userId : userIdsArr) {
            final TqAuthUserOrgRelationDO userOrgRelationDO =
                    new TqAuthUserOrgRelationDO(Integer.parseInt(userId),
                            orgId, AuthUtil.tenantId(), appId);
            insert = dao.insert(userOrgRelationDO);
        }
        if (userIdsArrDeleted.length != 0) {
            deleted = dao.delete(new QueryWrapper<TqAuthUserOrgRelationDO>().lambda()
                    .eq(TqAuthUserOrgRelationDO::getOrgId, orgId)
                    .in(TqAuthUserOrgRelationDO::getAppId, userIdsArrDeleted));
        }

        return deleted == userIdsArrDeleted.length && insert == userIdsArr.length;
    }

    @Override
    public ResultEntity<List<TqAuthUserOrgRelationDO>> loadAuthorizedUserListByAppIdTenantIdAndOrgId(
            final Integer tenantId, final Integer appId, final Integer orgId) {
        final List<TqAuthUserOrgRelationDO> tqAuthUserOrgRelationDOS =
                dao.selectList(new QueryWrapper<TqAuthUserOrgRelationDO>().lambda()
                        .eq(TqAuthUserOrgRelationDO::getTenantId, tenantId)
                        .eq(TqAuthUserOrgRelationDO::getAppId, appId)
                        .eq(TqAuthUserOrgRelationDO::getOrgId, orgId));
        return RestResult.<List<TqAuthUserOrgRelationDO>>builder().ok(true)
                .withData(tqAuthUserOrgRelationDOS).withStatus(
                        StatusEnum.OK).build();
    }
}
