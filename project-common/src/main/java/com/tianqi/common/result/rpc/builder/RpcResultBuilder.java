package com.tianqi.common.result.rpc.builder;

import com.tianqi.common.enums.database.RpcStatusEnum;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/18 21:55
 * @Description:
 */
public class RpcResultBuilder<R> {
    private RpcStatusEnum status;
    private R result;

    public RpcResultBuilder<R> withStatus(final RpcStatusEnum status) {
        this.status = status;
        return this;
    }

    public RpcResultBuilder<R> withResult(final R result) {
        this.result = result;
        return this;
    }

    public RpcResultEntity<R> build() {
        return new RpcResultEntity<R>(status, result);
    }
}
