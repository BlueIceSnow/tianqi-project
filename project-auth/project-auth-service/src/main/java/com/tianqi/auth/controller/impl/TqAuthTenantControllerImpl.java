package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthTenantController;
import com.tianqi.auth.pojo.TqAuthTenantDO;
import com.tianqi.auth.service.ITqAuthTenantService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租户表(TqAuthTenant)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:29
 */
@RestController
@RequestMapping("tqAuthTenant")
public class TqAuthTenantControllerImpl
        extends BaseControllerImpl<ITqAuthTenantService, TqAuthTenantDO>
        implements ITqAuthTenantController {

}
