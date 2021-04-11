package com.tianqi.movie.controller.impl;

import com.tianqi.movie.pojo.UserDO;
import com.tianqi.common.controller.impl.BaseController;
import com.tianqi.movie.service.IUserService;
import com.tianqi.movie.controller.IUserController;
import org.springframework.web.bind.annotation.*;

/**
 * (User)表控制层
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:15
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<IUserService, UserDO> implements IUserController {

}
