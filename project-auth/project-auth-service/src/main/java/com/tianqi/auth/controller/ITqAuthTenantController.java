package com.tianqi.auth.controller;

import com.tianqi.auth.pojo.TqAuthTenantDO;
import com.tianqi.auth.pojo.dto.req.TenantInfoDTO;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 租户表(TqAuthTenant)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:29
 */
public interface ITqAuthTenantController
        extends IBaseController<TqAuthTenantDO> {
    /**
     * 保存实体
     *
     * @param tenantInfoDTO 添加租户信息
     * @return 保存后的实体数据
     */
    @PostMapping("saveTenant")
    ResultEntity<TqAuthTenantDO> saveTenant(
            @RequestBody TenantInfoDTO tenantInfoDTO);
}
