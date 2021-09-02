package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthUserRoleRelationController;
import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.auth.service.ITqAuthUserRoleRelationService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色组关联表(TqAuthUserRoleRelation)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-31 11:08:05
 */
@RestController
@RequestMapping("tqAuthUserRoleRelation")
public class TqAuthUserRoleRelationControllerImpl extends
        BaseControllerImpl<ITqAuthUserRoleRelationService, TqAuthUserRoleRelationDO>
        implements ITqAuthUserRoleRelationController {

}
