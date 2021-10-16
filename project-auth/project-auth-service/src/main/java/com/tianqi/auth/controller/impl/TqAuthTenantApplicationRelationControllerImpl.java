package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthTenantApplicationRelationController;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.auth.service.ITqAuthTenantApplicationRelationService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租户应用关联表(TqAuthTenantApplicationRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:37
 */
@RestController
@RequestMapping("tqAuthTenantApplicationRelation")
public class TqAuthTenantApplicationRelationControllerImpl extends
        BaseControllerImpl<ITqAuthTenantApplicationRelationService, TqAuthTenantApplicationRelationDO>
        implements ITqAuthTenantApplicationRelationController {

}
