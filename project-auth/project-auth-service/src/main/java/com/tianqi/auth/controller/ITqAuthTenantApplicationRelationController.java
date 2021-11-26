package com.tianqi.auth.controller;

import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.auth.pojo.dto.req.TenantApplicationRelationDTO;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 租户应用关联表(TqAuthTenantApplicationRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:37
 */
public interface ITqAuthTenantApplicationRelationController
        extends IBaseController<TqAuthTenantApplicationRelationDO> {
    /**
     * 加载当前登录用户拥有的应用列表
     *
     * @return 当前租户拥有的应用列表
     */
    ResultEntity<List<TqAuthApplicationDO>> loadApplicationListByCurrentUser();

    /**
     * 加载当前租户拥有的应用列表
     *
     * @param tenantId 租户ID
     * @return
     */
    ResultEntity<List<TqAuthTenantApplicationRelationDO>> loadTenantRelationApplicationList(
            String tenantId);

    /**
     * 将应用授权给租户
     *
     * @param tenantApplicationRelationDTO 租户应用关系实体
     * @return
     */
    ResultEntity<Boolean> authorisedApplicationToTenant(
            TenantApplicationRelationDTO tenantApplicationRelationDTO);


    /**
     * 根据用户名加载应用列表
     *
     * @param username
     * @return 该用户所属租户下的应用列表
     */
    @GetMapping(value = "loadApplicationByUsername")
    ResultEntity<List<TqAuthApplicationDO>> loadApplicationByUsername(String username);

}
