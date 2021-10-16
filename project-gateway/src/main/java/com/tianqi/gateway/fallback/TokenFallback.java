package com.tianqi.gateway.fallback;

import com.tianqi.auth.api.fallback.AbstractDemoFallback;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/22 16:44
 * @Description:
 */
@Component
public class TokenFallback extends AbstractDemoFallback {
    @Override
    public RpcResultEntity<List<TqAuthUserDO>> listAll() {
        return null;
    }

    @Override
    public Map<String, Object> checkToken(final String value) {
        System.out.println(value);
        return null;
    }
}
