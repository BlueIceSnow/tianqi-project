package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthTenantDO;
import com.tianqi.auth.pojo.dto.req.TenantInfoDTO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;

/**
 * 租户表(TqAuthTenant)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:28
 */
public interface ITqAuthTenantService extends IBaseService<TqAuthTenantDO> {

    /**
     * 保存租户信息
     *
     * @param tenantInfoDTO 租户信息字段
     * @return
     */
    ResultEntity<TqAuthTenantDO> saveTenant(TenantInfoDTO tenantInfoDTO);

}
