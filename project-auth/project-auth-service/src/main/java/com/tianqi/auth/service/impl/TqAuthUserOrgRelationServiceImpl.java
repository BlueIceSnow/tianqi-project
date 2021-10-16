package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthUserOrgRelationDAO;
import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.pojo.TqAuthUserOrgRelationDO;
import com.tianqi.auth.service.ITqAuthUserOrgRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
