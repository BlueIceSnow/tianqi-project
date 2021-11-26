package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 角色资源关联表(TqAuthRoleResourceRelation)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:18
 */
public interface ITqAuthRoleResourceRelationService
        extends IBaseService<TqAuthRoleResourceRelationDO> {
    /**
     * 根据角色列表，检索与其对应的资源
     *
     * @param tenantId 租户ID
     * @param appId    应用ID
     * @return
     */
    Map<String, List<String>> selectResourceRoleMapping(final Integer tenantId,
                                                        final Integer appId);

    /**
     * 加载已授权资源，通过角色ID及资源类型
     *
     * @param roleId 角色ID
     * @param type   资源类型
     * @return
     */
    ResultEntity<List<TqAuthRoleResourceRelationDO>> loadAuthorisedResByRoleIdAndType(String roleId,
                                                                                      String type);

    /**
     * 建立角色与资源关系
     *
     * @param tenantId      租户ID
     * @param roleId        角色ID
     * @param resIdsArr     资源ID数组
     * @param resIdsDeleted 删除的关系
     * @return
     */
    boolean insertRoleResourceRelations(Integer tenantId, String roleId, String[] resIdsArr,
                                        String[] resIdsDeleted);
}
