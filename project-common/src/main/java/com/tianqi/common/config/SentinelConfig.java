package com.tianqi.common.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 限流配置
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/12/07 09:50:43
 */
@Configuration
@Slf4j
public class SentinelConfig {
    @Bean
    @ConditionalOnMissingBean(value = {BlockExceptionHandler.class})
    public BlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return (request, response, e) -> {
            log.error("服务被限流....", e);
            ResponseUtil.resJson(response,
                    RestResult.builder().withError(new BaseException("service is limit request"))
                            .withStatus(
                                    StatusEnum.SERVER_ERROR).build());
        };
    }
}
