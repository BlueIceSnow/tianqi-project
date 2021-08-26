package com.tianqi.auth.controller.impl;

import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.auth.controller.ITqAuthRoleResourceRelationController;
import org.springframework.web.bind.annotation.*;

/**
 * 角色资源关联表(TqAuthRoleResourceRelation)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:18
 */
@RestController
@RequestMapping("tqAuthRoleResourceRelation")
public class TqAuthRoleResourceRelationControllerImpl extends
        BaseControllerImpl<ITqAuthRoleResourceRelationService, TqAuthRoleResourceRelationDO>
        implements ITqAuthRoleResourceRelationController {

}
