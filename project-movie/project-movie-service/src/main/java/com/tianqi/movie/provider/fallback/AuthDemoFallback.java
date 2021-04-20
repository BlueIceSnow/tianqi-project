package com.tianqi.movie.provider.fallback;

import com.tianqi.auth.api.fallback.AbstractDemoFallback;
import com.tianqi.auth.pojo.UserDO;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rpc.RpcResult;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 22:20
 * @Description:
 */
@Component
public class AuthDemoFallback extends AbstractDemoFallback {
    @Override
    public RpcResultEntity<List<UserDO>> listAll() {
        System.out.println("出现异常！");
        return RpcResult.<List<UserDO>>builder()
                .withStatus(StatusEnum.BUS_ERROR)
                .withResult(new ArrayList<>()).build();
    }
}
