package com.tianqi.auth.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.tianqi.auth.dao.ITqAuthUserRoleGroupRelationDAO;
import com.tianqi.auth.pojo.TqAuthUserRoleGroupRelationDO;
import com.tianqi.auth.service.ITqAuthUserRoleGroupRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色组关联表(TqAuthUserRoleGroupRelation)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:52
 */
@Service
public class TqAuthUserRoleGroupRelationServiceImpl extends
        BaseServiceImpl<ITqAuthUserRoleGroupRelationDAO, TqAuthUserRoleGroupRelationDO>
        implements ITqAuthUserRoleGroupRelationService {
    private ITqAuthUserRoleGroupRelationDAO userRoleGroupRelationDAO;

    @Autowired
    public void setUserRoleGroupRelationDAO(
            final ITqAuthUserRoleGroupRelationDAO userRoleGroupRelationDAO) {
        this.userRoleGroupRelationDAO = userRoleGroupRelationDAO;
    }

    @Override
    public List<String> selectUserRoleListByUserId(final Integer userId,
                                                   final String orgCode) {
        final List<String> roleList = new ArrayList();
        final List<JSONArray> roleGroupList =
                userRoleGroupRelationDAO.selectUserRoleListByUserId(userId, orgCode);
        for (final JSONArray list : roleGroupList) {
            for (int i = 0; i < list.size(); i++) {
                roleList.add(list.getString(i));
            }
        }
        return roleList;
    }
}
