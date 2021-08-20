package com.tianqi.movie.provider;

import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
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
    public ResultEntity<List<UserDO>> listAll(UserDO userDO) {
        final List<UserDO> userDOS = userService.listEntity(userDO)
                .getData().doOrDto();
        return RestResult.<List<UserDO>>builder()
                .withStatus(StatusEnum.OK)
                .withData(userDOS)
                .build();
    }
}
