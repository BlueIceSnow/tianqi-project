package com.tianqi.movie.api;

import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import com.tianqi.movie.api.fallback.AbstractDemoFallback;
import com.tianqi.movie.pojo.UserDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 17:46
 * @Description:
 */
@FeignClient(name = "project-movie-service", fallback = AbstractDemoFallback.class)
public interface IDemoApi {

    /**
     * 查询用户列表
     *
     * @param userDO 查询参数
     * @return 用户列表
     */
    @GetMapping("user/listAll")
    RpcResultEntity<List<UserDO>> listAll(@SpringQueryMap UserDO userDO);
}
