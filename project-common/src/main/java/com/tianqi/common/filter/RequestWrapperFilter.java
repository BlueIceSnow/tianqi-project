package com.tianqi.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: 请求包装类.
 * @Author: ytq
 * @Date: 2021/10/25 11:18:07
 */
@Slf4j
public class RequestWrapperFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) {
        log.info("request wrapper created...");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        final ServletRequest requestWrapper = new RequestWrapper((HttpServletRequest) request);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        log.info("request wrapper destroyed...");
    }
}