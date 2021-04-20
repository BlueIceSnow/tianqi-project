package com.tianqi.auth.api;

import com.tianqi.auth.api.fallback.AbstractDemoFallback;
import com.tianqi.auth.pojo.UserDO;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 17:46
 * @Description:
 */
@FeignClient(name = "project-auth-service-dev", fallback = AbstractDemoFallback.class)
public interface IDemoApi {

    @GetMapping("user/listAll")
    RpcResultEntity<List<UserDO>> listAll();
}
