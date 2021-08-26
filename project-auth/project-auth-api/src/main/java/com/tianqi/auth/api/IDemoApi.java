package com.tianqi.auth.api;

import com.tianqi.auth.api.fallback.AbstractDemoFallback;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 17:46
 * @Description:
 */
@FeignClient(name = "project-auth-service", fallback = AbstractDemoFallback.class)
public interface IDemoApi {

    @GetMapping("user/listAll")
    RpcResultEntity<List<TqAuthUserDO>> listAll();

    /**
     * 校验token
     *
     * @param value token
     * @return
     */
    @RequestMapping(value = "/oauth/check_token")
    @ResponseBody
    Map<String, Object> checkToken(@RequestParam("token") String value);
}
