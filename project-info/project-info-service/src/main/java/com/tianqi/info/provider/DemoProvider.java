package com.tianqi.info.provider;

import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rpc.RpcResult;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import com.tianqi.info.api.IDemoApi;
import com.tianqi.info.pojo.UserDO;
import com.tianqi.info.service.IUserService;
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
    public RpcResultEntity<List<UserDO>> listAll(UserDO userDO) {
        final List<UserDO> userDOS = userService.listEntity(userDO)
                .getData().doOrDto();
        return RpcResult.<List<UserDO>>builder()
                .withStatus(StatusEnum.OK)
                .withResult(userDOS)
                .build();
    }
}
