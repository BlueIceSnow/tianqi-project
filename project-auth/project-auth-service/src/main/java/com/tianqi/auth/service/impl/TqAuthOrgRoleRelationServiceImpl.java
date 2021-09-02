package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthOrgRoleRelationDAO;
import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
import com.tianqi.auth.service.ITqAuthOrgRoleRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织角色关联表(TqAuthOrgRoleRelation)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:36
 */
@Service
public class TqAuthOrgRoleRelationServiceImpl
        extends BaseServiceImpl<ITqAuthOrgRoleRelationDAO, TqAuthOrgRoleRelationDO>
        implements ITqAuthOrgRoleRelationService {
    private ITqAuthOrgRoleRelationDAO orgRoleRelationDAO;

    @Autowired
    public void setOrgRoleRelationDAO(
            final ITqAuthOrgRoleRelationDAO orgRoleRelationDAO) {
        this.orgRoleRelationDAO = orgRoleRelationDAO;
    }

    @Override
    public List<String> selectOrgRoleListByOrgCode(final String orgCode) {
        return orgRoleRelationDAO.selectOrgRoleListByOrgCode(orgCode);
    }
}
