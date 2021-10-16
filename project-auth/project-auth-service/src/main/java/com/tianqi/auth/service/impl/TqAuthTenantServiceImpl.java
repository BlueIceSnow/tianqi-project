package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthTenantDAO;
import com.tianqi.auth.pojo.TqAuthTenantDO;
import com.tianqi.auth.service.ITqAuthTenantService;
import com.tianqi.common.service.impl.BaseServiceImpl;
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

}
