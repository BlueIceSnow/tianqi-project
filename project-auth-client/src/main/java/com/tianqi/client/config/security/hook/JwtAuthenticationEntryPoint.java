package com.tianqi.client.config.security.hook;

import com.tianqi.common.enums.AuthEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/17 19:10
 * @Description: 未登录回调函数
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException e)
            throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("not login");
        }
        final ResultEntity<Object> notLogin = RestResult.builder()
                .withError(new BaseException("not login"))
                .withStatus(AuthEnum.NOT_LOGIN)
                .build();
        ResponseUtil.resJson(response, notLogin);
    }
}
