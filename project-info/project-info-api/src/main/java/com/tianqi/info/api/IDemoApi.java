package com.tianqi.info.api;

import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import com.tianqi.info.pojo.UserDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 17:46
 * @Description:
 */
@FeignClient(name = "project-info-service", url = "127.0.0.1:8080", path = "service")
public interface IDemoApi {

    /**
     * 查询所有用户
     *
     * @param userDO 查询参数
     * @return 响应用户列表
     */
    @PostMapping("/user/listAll")
    RpcResultEntity<List<UserDO>> listAll(@RequestBody UserDO userDO);
}
