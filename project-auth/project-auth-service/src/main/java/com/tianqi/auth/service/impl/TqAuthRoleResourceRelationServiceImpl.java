package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthRoleResourceRelationDAO;
import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.pojo.dto.resp.ResourceRoleDTO;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.common.constant.SystemConstant;
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
}
