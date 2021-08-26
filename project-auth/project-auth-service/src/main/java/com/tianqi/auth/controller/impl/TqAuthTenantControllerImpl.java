package com.tianqi.auth.controller.impl;

import com.tianqi.auth.pojo.TqAuthTenantDO;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.auth.service.ITqAuthTenantService;
import com.tianqi.auth.controller.ITqAuthTenantController;
import org.springframework.web.bind.annotation.*;

/**
 * 租户表(TqAuthTenant)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:29
 */
@RestController
@RequestMapping("tqAuthTenant")
public class TqAuthTenantControllerImpl
        extends BaseControllerImpl<ITqAuthTenantService, TqAuthTenantDO>
        implements ITqAuthTenantController {

}
