package com.tianqi.common.result.rpc;

import com.tianqi.common.result.rpc.builder.RpcResultBuilder;
import lombok.Data;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/18 21:15
 * @Description:
 */
@Data
public final class RpcResult {

    public static <Entity> RpcResultBuilder<Entity> builder() {
        return new RpcResultBuilder<Entity>();
    }
}
