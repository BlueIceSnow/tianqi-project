package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthRoleResourceRelationDAO;
import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.pojo.dto.resp.ResourceRoleDTO;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.client.util.AuthUtil;
import com.tianqi.common.constant.SystemConstant;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色资源关联表(TqAuthRoleResourceRelation)表服务实现类.
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:18
 */
@Service
public class TqAuthRoleResourceRelationServiceImpl extends
        BaseServiceImpl<ITqAuthRoleResourceRelationDAO,
                TqAuthRoleResourceRelationDO>
        implements ITqAuthRoleResourceRelationService {
    /**
     * 角色资源关系管理.
     */
    private ITqAuthRoleResourceRelationDAO roleResourceRelationDAO;

    /**
     * 注入角色资源管理持久层操作类.
     *
     * @param roleResourceRelationDAO 角色资源持久层操作类
     */
    @Autowired
    public void setRoleResourceRelationDAO(
            final ITqAuthRoleResourceRelationDAO roleResourceRelationDAO) {
        this.roleResourceRelationDAO = roleResourceRelationDAO;
    }

    /**
     * 根据租户ID及应用ID查询所有角色资源映射关系.
     *
     * @param tenantId 租户ID
     * @param appId    应用ID
     * @return
     */
    @Override
    public Map<String, List<String>> selectResourceRoleMapping(
            final Integer tenantId, final Integer appId) {
        final List<ResourceRoleDTO> resourceRoleDTOS =
                dao.selectResourceRoleMapping(tenantId, appId);
        final Map<String, List<String>> result = new HashMap<>();

        for (final ResourceRoleDTO resourceRoleDTO : resourceRoleDTOS) {
            result.put(resourceRoleDTO.getUrl() + SystemConstant.REDIS_SPLIT +
                            resourceRoleDTO.getAppId() +
                            SystemConstant.REDIS_SPLIT +
                            resourceRoleDTO.getTenantId(),
                    resourceRoleDTO.getRoles().stream()
                            .map(TqAuthRoleDO::getCode)
                            .collect(Collectors.toList()));
        }

        return result;
    }

    @Override
    public ResultEntity<List<TqAuthRoleResourceRelationDO>> loadAuthorisedResByRoleIdAndType(
            final String roleId,
            final String type) {


        return RestResult.<List<TqAuthRoleResourceRelationDO>>builder()
                .withData(dao.loadAuthorisedResByRoleIdAndType(roleId, type)).ok(true)
                .withStatus(StatusEnum.OK)
                .build();
    }

    @Override
    public boolean insertRoleResourceRelations(final Integer tenantId, final String roleId,
                                               final Integer appId,
                                               final String[] resIdsArr,
                                               final String[] resIdsDeleted) {
        int insert = 0;
        int deleted = 0;
        for (final String resId : resIdsArr) {
            final TqAuthRoleResourceRelationDO tqAuthRoleResourceRelationDO =
                    new TqAuthRoleResourceRelationDO(Integer.parseInt(roleId),
                            Integer.parseInt(resId), tenantId);
            insert = dao.insert(tqAuthRoleResourceRelationDO);
        }
        if (resIdsDeleted.length != 0) {
            deleted = dao.delete(new QueryWrapper<TqAuthRoleResourceRelationDO>().lambda()
                    .eq(TqAuthRoleResourceRelationDO::getRoleId, roleId)
                    .in(TqAuthRoleResourceRelationDO::getResourceId, resIdsDeleted));
        }
        AuthUtil.invalidateAuthorityCache(tenantId, appId);
        return deleted == resIdsDeleted.length && insert == resIdsArr.length;
    }
}
