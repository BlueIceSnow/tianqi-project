package com.tianqi.client.config.security.hook;

import com.tianqi.common.enums.business.AuthEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/18 15:25
 * @Description: 未授权回调函数
 */
@Component
@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(final HttpServletRequest request,
                       final HttpServletResponse response,
                       final AccessDeniedException e)
            throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("access denied");
        }
        final ResultEntity<Object> accessDenied = RestResult.builder()
                .withError(new BaseException("access denied"))
                .withStatus(AuthEnum.AUTHORIZE_FAIL)
                .build();
        ResponseUtil.resJson(response, accessDenied);
    }
}
