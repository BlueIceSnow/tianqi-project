package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthTenantDAO;
import com.tianqi.auth.pojo.TqAuthTenantDO;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.auth.pojo.dto.req.TenantInfoDTO;
import com.tianqi.auth.service.ITqAuthTenantService;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 租户表(TqAuthTenant)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:28
 */
@Service
public class TqAuthTenantServiceImpl
        extends BaseServiceImpl<ITqAuthTenantDAO, TqAuthTenantDO>
        implements ITqAuthTenantService {

    private TqAuthUserServiceImpl userService;

    @Autowired
    public void setUserService(final TqAuthUserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResultEntity<TqAuthTenantDO> saveTenant(
            final TenantInfoDTO tenantInfoDTO) {
        // 首先存储租户信息
        final int insert = dao.insert(tenantInfoDTO);
        // 存储用户信息
        final TqAuthUserDO mgr = tenantInfoDTO.getMgr();
        mgr.setTenantId(tenantInfoDTO.getId());
        mgr.setType("T");
        final ResultEntity<TqAuthUserDO> save = userService.save(mgr);
        // 建立租户及用户关系
        tenantInfoDTO.setMgrId(save.getData().doOrDto().getId());

        // 将普通租户权限授予给他
        
        final int update = dao.updateById(tenantInfoDTO);
        final boolean isOk = save.getStatus() == StatusEnum.OK && update == 1;
        return RestResult.<com.tianqi.auth.pojo.TqAuthTenantDO>builder()
                .withStatus(isOk ? StatusEnum.OK : StatusEnum.SERVER_ERROR)
                .withData(tenantInfoDTO)
                .ok(isOk)
                .build();
    }
}
