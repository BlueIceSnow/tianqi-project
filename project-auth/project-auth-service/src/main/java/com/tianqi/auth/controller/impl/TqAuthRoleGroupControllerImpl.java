package com.tianqi.auth.controller.impl;

import com.tianqi.auth.pojo.TqAuthRoleGroupDO;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.auth.service.ITqAuthRoleGroupService;
import com.tianqi.auth.controller.ITqAuthRoleGroupController;
import org.springframework.web.bind.annotation.*;

/**
 * 角色组表(TqAuthRoleGroup)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:09
 */
@RestController
@RequestMapping("tqAuthRoleGroup")
public class TqAuthRoleGroupControllerImpl
        extends BaseControllerImpl<ITqAuthRoleGroupService, TqAuthRoleGroupDO>
        implements ITqAuthRoleGroupController {

}
