package com.tianqi.auth.provider;

import com.tianqi.auth.api.IDemoApi;
import com.tianqi.auth.pojo.UserDO;
import com.tianqi.auth.service.IUserService;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rpc.RpcResult;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 18:12
 * @Description:
 */
@RestController
public class DemoProvider{
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public RpcResultEntity<List<UserDO>> listAll() {
//        int i = 1 / 0;
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<UserDO> userDOS = userService.listEntity(new UserDO()).getData().doOrDto();
        return RpcResult.<List<UserDO>>builder().withStatus(StatusEnum.OK).withResult(userDOS).build();
//        return RestResult.<List<UserDO>>builderPage().withStatus(StatusEnum.OK).withRows().build();
    }
}
