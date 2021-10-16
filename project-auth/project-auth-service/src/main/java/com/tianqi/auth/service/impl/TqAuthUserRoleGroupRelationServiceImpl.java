package com.tianqi.auth.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.tianqi.auth.dao.ITqAuthUserRoleGroupRelationDAO;
import com.tianqi.auth.pojo.TqAuthUserRoleGroupRelationDO;
import com.tianqi.auth.service.ITqAuthUserRoleGroupRelationService;
import com.tianqi.auth.service.ITqAuthUserRoleRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户角色组关联表(TqAuthUserRoleGroupRelation)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:52
 */
@Service
public class TqAuthUserRoleGroupRelationServiceImpl extends
        BaseServiceImpl<ITqAuthUserRoleGroupRelationDAO, TqAuthUserRoleGroupRelationDO>
        implements ITqAuthUserRoleGroupRelationService {
    private ITqAuthUserRoleGroupRelationDAO userRoleGroupRelationDAO;
    private ITqAuthUserRoleRelationService userRoleRelationService;

    @Autowired
    public void setUserRoleGroupRelationDAO(
            final ITqAuthUserRoleGroupRelationDAO userRoleGroupRelationDAO) {
        this.userRoleGroupRelationDAO = userRoleGroupRelationDAO;
    }

    @Autowired
    public void setUserRoleRelationService(
            final ITqAuthUserRoleRelationService userRoleRelationService) {
        this.userRoleRelationService = userRoleRelationService;
    }

    @Override
    public Set<String> selectUserRoleListByUserIdAndAppId(final Integer userId,
                                                          final Integer appId) {
        final List<JSONArray> roleGroupList =
                userRoleGroupRelationDAO
                        .selectUserRoleListInRoleGroupByUserIdAndAppId(userId,
                                appId);
        final List<String> userRoleList =
                userRoleRelationService
                        .selectUserRoleListByUserIdAndAppId(userId, appId);
        final Set<String> roleList = new HashSet<>(userRoleList);
        for (final JSONArray list : roleGroupList) {
            for (int i = 0; i < list.size(); i++) {
                roleList.add(list.getString(i));
            }
        }
        return roleList;
    }
}
