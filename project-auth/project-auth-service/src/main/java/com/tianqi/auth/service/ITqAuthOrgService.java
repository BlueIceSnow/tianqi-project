package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;

import java.util.List;

/**
 * 组织架构表(TqAuthOrg)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:24
 */
public interface ITqAuthOrgService extends IBaseService<TqAuthOrgDO> {
    /**
     * 获取用户所属组织信息
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     * @param appKey   应用ID
     * @return
     */
    TqAuthOrgDO selectUserOrgByUserIdAndTenantIdAndAppKey(final Integer userId,
                                                          final Integer tenantId,
                                                          final String appKey);

    /**
     * 根据租户、应用、所属组织，查询其组织树
     *
     * @param tenantId
     * @param appId
     * @param orgCode
     * @return
     */
    ResultEntity<List<TqAuthOrgDO>> listOrgByAppIdAndTenantIdAndCurrentLoginUserOrg(
            Integer tenantId, Integer appId, String orgCode);

    /**
     * 根据租户ID及应用ID拉取组织列表
     *
     * @param tenantId 租户ID
     * @param appId    应用ID
     * @return
     */
    ResultEntity<List<TqAuthOrgDO>> listOrgByTenantIdAndAppId(Integer tenantId, Integer appId);
}
