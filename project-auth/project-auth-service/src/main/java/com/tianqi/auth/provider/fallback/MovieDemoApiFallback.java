package com.tianqi.auth.provider.fallback;

import com.tianqi.common.result.rpc.RpcResult;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import com.tianqi.movie.api.fallback.AbstractDemoFallback;
import com.tianqi.movie.pojo.UserDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/12/10 10:48:50
 */
@Component
public class MovieDemoApiFallback extends AbstractDemoFallback {
    @Override
    public RpcResultEntity<List<UserDO>> listAll(final UserDO userDO) {
        return RpcResult.<List<UserDO>>builder().build();
    }
}
