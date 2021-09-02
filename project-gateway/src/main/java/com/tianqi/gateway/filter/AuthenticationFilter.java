package com.tianqi.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianqi.auth.api.IDemoApi;
import com.tianqi.common.enums.AuthEnum;
import com.tianqi.common.result.rest.RestResult;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/22 09:20
 * @Description:
 */

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {
    static private final Charset CHARACTER_SET = StandardCharsets.ISO_8859_1;
    private ObjectMapper mapper;
    @Autowired
    @Lazy
    private IDemoApi demoApi;

    @Autowired
    public void setMapper(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange,
                             final GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final String authentication = request.getHeaders().getFirst("Authorization");
        final byte[] resultBytes = mapper.writeValueAsString(RestResult.builder()
                .withData(new HashMap<>(0)).isOk(false).withStatus(AuthEnum.LOGIN_FAIL)
                .build())
                .getBytes(StandardCharsets.UTF_8);
        final DataBuffer wrap = exchange.getResponse().bufferFactory().wrap(resultBytes);
        if (StringUtils.isEmpty(authentication)) {
            exchange.getResponse().getHeaders()
                    .setContentType(MediaType.APPLICATION_JSON);
            return exchange.getResponse().writeWith(Flux.just(wrap));
        }
        // 拥有用户的TOKEN
        try {
            final Map<String, Object> checkResult =
                    demoApi.checkToken(authentication.replace("Bearer ", ""));

        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 8000;
    }

}