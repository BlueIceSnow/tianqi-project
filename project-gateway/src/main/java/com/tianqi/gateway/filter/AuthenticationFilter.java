package com.tianqi.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianqi.auth.api.IDemoApi;
import com.tianqi.common.enums.AuthEnums;
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
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst("Authorization");
        byte[] resultBytes = mapper.writeValueAsString(RestResult.builder()
                .withData(new HashMap<>()).isOk(false).withStatus(AuthEnums.LOGIN_FAIL)
                .build())
                .getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = exchange.getResponse().bufferFactory().wrap(resultBytes);
        if (StringUtils.isEmpty(authentication)) {
            exchange.getResponse().getHeaders()
                    .setContentType(MediaType.APPLICATION_JSON);
            return exchange.getResponse().writeWith(Flux.just(wrap));
        }
        // 拥有用户的TOKEN
        try {
            Map<String, Object> checkResult =
                    demoApi.checkToken(authentication.replace("Bearer ", ""));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 8000;
    }

}