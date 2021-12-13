package com.tianqi.movie.provider.fallback;

import com.tianqi.auth.api.fallback.AbstractDemoFallback;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.common.enums.database.RpcStatusEnum;
import com.tianqi.common.result.rpc.RpcResult;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 22:20
 * @Description:
 */
@Component
public class AuthDemoFallback extends AbstractDemoFallback {
    @Override
    public RpcResultEntity<List<TqAuthUserDO>> listAll() {
        System.out.println("出现异常！");
        return RpcResult.<List<TqAuthUserDO>>builder()
                .withStatus(RpcStatusEnum.ERROR)
                .withResult(new ArrayList<>()).build();
    }

    @Override
    public Map<String, Object> checkToken(final String value) {
        return null;
    }
}
