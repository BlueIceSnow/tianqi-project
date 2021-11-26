package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.pojo.dto.resp.ResourceRoleDTO;
import com.tianqi.common.dao.IBaseDAO;

import java.util.List;

/**
 * 角色资源关联表(TqAuthRoleResourceRelation)表数据库访问层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:18
 */
public interface ITqAuthRoleResourceRelationDAO
        extends IBaseDAO<TqAuthRoleResourceRelationDO> {
    /**
     * 根据租户ID及应用ID查询资源与角色映射
     *
     * @param tenantId
     * @param appId
     * @return
     */
    List<ResourceRoleDTO> selectResourceRoleMapping(Integer tenantId,
                                                    Integer appId);

    /**
     * 根据角色ID及资源类型加载资源
     *
     * @param roleId 角色ID
     * @param type   资源类型
     * @return
     */
    List<TqAuthRoleResourceRelationDO> loadAuthorisedResByRoleIdAndType(String roleId, String type);
}

