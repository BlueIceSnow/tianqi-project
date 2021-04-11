package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.IUserController;
import com.tianqi.auth.pojo.UserDO;
import com.tianqi.auth.service.IUserService;
import com.tianqi.common.controller.impl.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (User)表控制层
 *
 * @author yuantianqi
 * @since 2021-04-11 17:33:51
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<IUserService, UserDO> implements IUserController {
}