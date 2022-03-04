package com.tianqi.info.provider.fallback;

import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import com.tianqi.info.api.fallback.AbstractDemoFallback;
import com.tianqi.info.pojo.UserDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 22:20
 * @Description:
 */
@Component
public class AuthDemoFallback extends AbstractDemoFallback {


    @Override
    public RpcResultEntity<List<UserDO>> listAll(final UserDO userDO) {
        return null;
    }
}
