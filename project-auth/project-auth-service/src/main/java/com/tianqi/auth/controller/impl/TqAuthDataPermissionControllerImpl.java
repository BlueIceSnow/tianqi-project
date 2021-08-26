package com.tianqi.auth.controller.impl;

import com.tianqi.auth.pojo.TqAuthDataPermissionDO;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.auth.service.ITqAuthDataPermissionService;
import com.tianqi.auth.controller.ITqAuthDataPermissionController;
import org.springframework.web.bind.annotation.*;

/**
 * 数据权限表(TqAuthDataPermission)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:13
 */
@RestController
@RequestMapping("tqAuthDataPermission")
public class TqAuthDataPermissionControllerImpl
        extends BaseControllerImpl<ITqAuthDataPermissionService, TqAuthDataPermissionDO>
        implements ITqAuthDataPermissionController {

}
