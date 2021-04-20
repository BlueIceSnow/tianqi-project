package com.tianqi.movie.provider;

import com.tianqi.movie.api.IDemoApi;
import com.tianqi.movie.pojo.UserDO;
import com.tianqi.movie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 18:12
 * @Description:
 */
@RestController
public class DemoProvider implements IDemoApi {
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserDO> listAll(UserDO userDO) {
        return userService.listEntity(userDO)
                .getData().doOrDto();
    }
}
