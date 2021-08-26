package com.tianqi.auth.controller.impl;

import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.auth.service.ITqAuthUserService;
import com.tianqi.auth.controller.ITqAuthUserController;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表(TqAuthUser)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:44
 */
@RestController
@RequestMapping("tqAuthUser")
public class TqAuthUserControllerImpl
        extends BaseControllerImpl<ITqAuthUserService, TqAuthUserDO>
        implements ITqAuthUserController {

}
