package com.tianqi.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.fastjson.JSON;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/22 13:54
 * @Description:
 */
@Configuration
@Slf4j
public class GatewayConfig {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;


    public GatewayConfig(final ObjectProvider<List<ViewResolver>> viewResolversProvider,
                         final ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }


    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(
            final ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        GatewayCallbackManager.setBlockHandler((exchange, throwable) -> {
            log.error("服务转发发生异常....", throwable);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(
                    JSON.toJSONString(
                            RestResult.builder().withData(false)
                                    .withStatus(StatusEnum.SERVER_ERROR)
                                    .withError(new BaseException("服务已被限流")).build()));
        });
        final SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler =
                new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
        return sentinelGatewayBlockExceptionHandler;
    }

    @Bean
    public SentinelGatewayFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }
}
