package com.tianqi.client.config.security.hook;

import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.util.ResponseUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/26 09:17
 * @Description:
 */
public class FilterExceptionProcessor extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (final Exception ex) {
            logger.error(ex);
            final ResultEntity<Object> build = RestResult.builder()
                    .withError(new BaseException(ex.getMessage()))
                    .withStatus(StatusEnum.SERVER_ERROR)
                    .build();
            ResponseUtil.resJson(response, build);
        }
    }
}
