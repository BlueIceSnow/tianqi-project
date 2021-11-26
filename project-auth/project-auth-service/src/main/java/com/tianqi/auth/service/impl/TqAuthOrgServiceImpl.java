package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthOrgDAO;
import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.service.ITqAuthOrgService;
import com.tianqi.auth.service.ITqAuthUserOrgRelationService;
import com.tianqi.auth.util.AuthUtil;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织架构表(TqAuthOrg)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:24
 */
@Service
public class TqAuthOrgServiceImpl
        extends BaseServiceImpl<ITqAuthOrgDAO, TqAuthOrgDO>
        implements ITqAuthOrgService {
    private ITqAuthUserOrgRelationService userOrgRelationService;

    @Autowired
    public void setUserOrgRelationService(
            final ITqAuthUserOrgRelationService userOrgRelationService) {
        this.userOrgRelationService = userOrgRelationService;
    }

    /**
     * 检索用户所属组织
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     * @param appKey   应用ID
     * @return
     */
    @Override
    public TqAuthOrgDO selectUserOrgByUserIdAndTenantIdAndAppKey(
            final Integer userId,
            final Integer tenantId,
            final String appKey) {
        return userOrgRelationService
                .selectUserOrgByTenantIdAndAppKey(userId, tenantId, appKey);
    }

    @Override
    public ResultEntity<List<TqAuthOrgDO>> listOrgByAppIdAndTenantIdAndCurrentLoginUserOrg(
            final Integer tenantId, final Integer appId, final String orgCode) {

        final List<TqAuthOrgDO> tqAuthOrgDOS =
                dao.listOrgByAppIdAndTenantIdAndCurrentLoginUserOrg(tenantId, appId, orgCode);
        return RestResult.<List<TqAuthOrgDO>>builder()
                .withData(tqAuthOrgDOS)
                .ok(true)
                .withStatus(StatusEnum.OK)
                .build();
    }

    @Override
    public ResultEntity<List<TqAuthOrgDO>> listOrgByTenantIdAndAppId(final Integer tenantId,
                                                                     final Integer appId) {
        final List<TqAuthOrgDO> tqAuthOrgDOS =
                dao.listOrgByAppIdAndTenantId(tenantId, appId);
        return RestResult.<List<TqAuthOrgDO>>builder()
                .withData(tqAuthOrgDOS)
                .ok(true)
                .withStatus(StatusEnum.OK)
                .build();
    }

    @Override
    public ResultEntity<TqAuthOrgDO> save(final TqAuthOrgDO entity) {
        if (entity.getTenantId() == null) {
            entity.setTenantId(AuthUtil.tenantId());
        }
        return super.save(entity);
    }
}
