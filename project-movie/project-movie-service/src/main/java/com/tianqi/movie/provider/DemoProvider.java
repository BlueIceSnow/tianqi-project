package com.tianqi.movie.provider;

import com.tianqi.common.enums.database.RpcStatusEnum;
import com.tianqi.common.result.rpc.RpcResult;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
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
    public void setUserService(final IUserService userService) {
        this.userService = userService;
    }

    @Override
    public RpcResultEntity<List<UserDO>> listAll(final UserDO userDO) {
        final List<UserDO> userDOS = userService.listEntity(userDO)
                .getData().doOrDto();
        return RpcResult.<List<UserDO>>builder().withResult(userDOS)
                .withStatus(RpcStatusEnum.OK)
                .build();
    }
}
