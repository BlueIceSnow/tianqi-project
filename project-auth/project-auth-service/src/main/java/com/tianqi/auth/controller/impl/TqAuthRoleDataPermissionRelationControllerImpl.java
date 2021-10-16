package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthRoleDataPermissionRelationController;
import com.tianqi.auth.pojo.TqAuthRoleDataPermissionRelationDO;
import com.tianqi.auth.service.ITqAuthRoleDataPermissionRelationService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色数据权限关联表(TqAuthRoleDataPermissionRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:02
 */
@RestController
@RequestMapping("tqAuthRoleDataPermissionRelation")
public class TqAuthRoleDataPermissionRelationControllerImpl extends
        BaseControllerImpl<ITqAuthRoleDataPermissionRelationService, TqAuthRoleDataPermissionRelationDO>
        implements ITqAuthRoleDataPermissionRelationController {

}
