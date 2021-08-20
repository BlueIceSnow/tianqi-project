package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqUserController;
import com.tianqi.auth.pojo.TqUserDO;
import com.tianqi.auth.service.ITqUserService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
public class TqUserController extends BaseControllerImpl<ITqUserService, TqUserDO>
        implements ITqUserController {

    @GetMapping("test")
    @PreAuthorize("hasRole('ADMIN')")
    public String test() {
        return "Test!";
    }
}