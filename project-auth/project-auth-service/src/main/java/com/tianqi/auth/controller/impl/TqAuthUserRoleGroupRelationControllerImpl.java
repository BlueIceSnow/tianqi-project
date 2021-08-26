package com.tianqi.auth.controller.impl;

import com.tianqi.auth.pojo.TqAuthUserRoleGroupRelationDO;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.auth.service.ITqAuthUserRoleGroupRelationService;
import com.tianqi.auth.controller.ITqAuthUserRoleGroupRelationController;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色组关联表(TqAuthUserRoleGroupRelation)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:52
 */
@RestController
@RequestMapping("tqAuthUserRoleGroupRelation")
public class TqAuthUserRoleGroupRelationControllerImpl extends
        BaseControllerImpl<ITqAuthUserRoleGroupRelationService, TqAuthUserRoleGroupRelationDO>
        implements ITqAuthUserRoleGroupRelationController {

}
