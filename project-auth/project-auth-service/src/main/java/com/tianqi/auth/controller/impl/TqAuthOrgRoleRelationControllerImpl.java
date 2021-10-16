package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthOrgRoleRelationController;
import com.tianqi.auth.pojo.TqAuthOrgRoleRelationDO;
import com.tianqi.auth.service.ITqAuthOrgRoleRelationService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织角色关联表(TqAuthOrgRoleRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:36
 */
@RestController
@RequestMapping("tqAuthOrgRoleRelation")
public class TqAuthOrgRoleRelationControllerImpl
        extends
        BaseControllerImpl<ITqAuthOrgRoleRelationService, TqAuthOrgRoleRelationDO>
        implements ITqAuthOrgRoleRelationController {

}
