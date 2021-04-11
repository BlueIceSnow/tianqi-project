package com.tianqi.common.result.rest;

import com.tianqi.common.result.rest.builder.PageResultBuilder;
import com.tianqi.common.result.rest.builder.RestResultBuilder;

/**
 * 统一响应结果
 *
 * @author yuantianqi
 */
public final class RestResult {
    public static <Entity> RestResultBuilder<Entity> builder() {
        return new RestResultBuilder<>();
    }
    public static <Entity> PageResultBuilder<Entity> builderPage() {
        return new PageResultBuilder<>();
    }

}
