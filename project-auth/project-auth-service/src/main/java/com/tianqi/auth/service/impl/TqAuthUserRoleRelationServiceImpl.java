package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthUserRoleRelationDAO;
import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.auth.service.ITqAuthUserRoleRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色组关联表(TqAuthUserRoleRelation)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-31 11:08:04
 */
@Service
public class TqAuthUserRoleRelationServiceImpl
        extends
        BaseServiceImpl<ITqAuthUserRoleRelationDAO, TqAuthUserRoleRelationDO>
        implements ITqAuthUserRoleRelationService {

    private ITqAuthUserRoleRelationDAO userRoleRelationDAO;

    @Autowired
    public void setUserRoleRelationDAO(
            final ITqAuthUserRoleRelationDAO userRoleRelationDAO) {
        this.userRoleRelationDAO = userRoleRelationDAO;
    }

    /**
     * 根据用户ID 及 AppId获取用户拥有的角色
     *
     * @param userId 用户ID
     * @param appId  应用ID
     * @return
     */
    @Override
    public List<String> selectUserRoleListByUserIdAndAppId(final Integer userId,
                                                           final Integer appId) {
        return userRoleRelationDAO
                .selectUserRoleListByUserIdAndAppId(userId, appId);
    }
}
