package com.tianqi.movie.api;

import com.tianqi.movie.api.fallback.AbstractDemoFallback;
import com.tianqi.movie.pojo.UserDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 17:46
 * @Description:
 */
@FeignClient(name = "project-movie-service-dev", fallback = AbstractDemoFallback.class)
public interface IDemoApi {

    @GetMapping("user/listAll")
    List<UserDO> listAll(UserDO userDO);
}
