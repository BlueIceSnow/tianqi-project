package com.tianqi.auth.controller.impl;

import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.auth.service.ITqAuthRoleService;
import com.tianqi.auth.controller.ITqAuthRoleController;
import org.springframework.web.bind.annotation.*;

/**
 * 角色表(TqAuthRole)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:54
 */
@RestController
@RequestMapping("tqAuthRole")
public class TqAuthRoleControllerImpl
        extends BaseControllerImpl<ITqAuthRoleService, TqAuthRoleDO>
        implements ITqAuthRoleController {

}
