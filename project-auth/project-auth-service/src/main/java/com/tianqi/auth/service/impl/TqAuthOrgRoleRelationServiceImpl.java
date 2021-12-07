package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthOrgRoleRelationDAO;
import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
import com.tianqi.auth.service.ITqAuthOrgRoleRelationService;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织角色关联表(TqAuthOrgRoleRelation)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:36
 */
@Service
public class TqAuthOrgRoleRelationServiceImpl
        extends
        BaseServiceImpl<ITqAuthOrgRoleRelationDAO, TqAuthOrgRoleRelationDO>
        implements ITqAuthOrgRoleRelationService {
    private ITqAuthOrgRoleRelationDAO orgRoleRelationDAO;

    @Autowired
    public void setOrgRoleRelationDAO(
            final ITqAuthOrgRoleRelationDAO orgRoleRelationDAO) {
        this.orgRoleRelationDAO = orgRoleRelationDAO;
    }

    /**
     * 检索用户加入组织后拥有的角色
     *
     * @param orgId 组织ID
     * @return
     */
    @Override
    public List<String> selectOrgRoleListByOrgId(final Integer orgId) {
        return orgRoleRelationDAO.selectOrgRoleListByOrgId(orgId);
    }

    @Override
    public boolean insertOrgRoleRelations(final Integer orgId, final Integer appId,
                                          final String[] roleIdsArr,
                                          final String[] roleIdsDeletedArr) {
        int insert = 0;
        int deleted = 0;
        for (final String roleId : roleIdsArr) {
            final TqAuthOrgRoleRelationDO userOrgRelationDO =
                    new TqAuthOrgRoleRelationDO(Integer.parseInt(roleId), orgId);
            insert = dao.insert(userOrgRelationDO);
        }
        if (roleIdsDeletedArr.length != 0) {
            deleted = dao.delete(new QueryWrapper<TqAuthOrgRoleRelationDO>().lambda()
                    .eq(TqAuthOrgRoleRelationDO::getOrgId, orgId)
                    .in(TqAuthOrgRoleRelationDO::getRoleId, roleIdsDeletedArr));
        }

        return deleted == roleIdsDeletedArr.length && insert == roleIdsArr.length;
    }

    @Override
    public ResultEntity<List<TqAuthOrgRoleRelationDO>> loadAuthorizedRoleListByAppIdTenantIdAndOrgId(
            final Integer tenantId, final Integer appId, final Integer orgId) {
        final List<TqAuthOrgRoleRelationDO> tqAuthUserOrgRelationDOS =
                dao.selectList(new QueryWrapper<TqAuthOrgRoleRelationDO>().lambda()
                        .eq(TqAuthOrgRoleRelationDO::getOrgId, orgId));
        return RestResult.<List<TqAuthOrgRoleRelationDO>>builder().ok(true)
                .withData(tqAuthUserOrgRelationDOS).withStatus(
                        StatusEnum.OK).build();
    }
}
