package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthUserRoleRelationDAO;
import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.auth.service.ITqAuthUserRoleRelationService;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
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

    @Override
    public boolean insertUserRoleRelations(final Integer userId, final Integer appId,
                                           final String[] roleIdsArr,
                                           final String[] roleIdsDeletedArr) {
        int insert = 0;
        int deleted = 0;
        for (final String roleId : roleIdsArr) {
            final TqAuthUserRoleRelationDO userRoleRelationDOr =
                    new TqAuthUserRoleRelationDO(Integer.parseInt(roleId), userId, appId);
            insert = dao.insert(userRoleRelationDOr);
        }
        if (roleIdsDeletedArr.length != 0) {
            deleted = dao.delete(new QueryWrapper<TqAuthUserRoleRelationDO>().lambda()
                    .eq(TqAuthUserRoleRelationDO::getUserId, userId)
                    .in(TqAuthUserRoleRelationDO::getRoleId, roleIdsDeletedArr));
        }

        return deleted == roleIdsDeletedArr.length && insert == roleIdsArr.length;
    }

    @Override
    public ResultEntity<List<TqAuthUserRoleRelationDO>> loadAuthorizedRoleListByAppIdAndUserId(
            final Integer userId, final Integer appId) {
        final List<TqAuthUserRoleRelationDO> tqAuthUserRoleRelationDOS =
                dao.selectList(new QueryWrapper<TqAuthUserRoleRelationDO>().lambda()
                        .eq(TqAuthUserRoleRelationDO::getUserId, userId)
                        .eq(TqAuthUserRoleRelationDO::getAppId, appId));
        return RestResult.<List<TqAuthUserRoleRelationDO>>builder().ok(true)
                .withData(tqAuthUserRoleRelationDOS).withStatus(
                        StatusEnum.OK).build();
    }
}
