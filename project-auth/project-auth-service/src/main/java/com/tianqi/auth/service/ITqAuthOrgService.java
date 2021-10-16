package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.common.service.IBaseService;

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
}
