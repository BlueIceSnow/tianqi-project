package com.tianqi.info.api;

import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import com.tianqi.info.api.fallback.AbstractDemoFallback;
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
@FeignClient(name = "project-info-service", path = "service", fallback =
        AbstractDemoFallback.class)
public interface IDemoApi {

    @PostMapping("/user/listAll")
    RpcResultEntity<List<UserDO>> listAll(@RequestBody UserDO userDO);
}
