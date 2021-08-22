package com.tianqi.auth.config.security.hook;

import cn.hutool.core.date.DateField;
import com.tianqi.common.enums.AuthEnums;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.util.ResponseUtil;
import com.tianqi.common.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/18 10:23
 * @Description: 登录回调函数
 */
@Component
@Slf4j
public class JwtLoginHandler implements AuthenticationFailureHandler,
        AuthenticationSuccessHandler, LogoutSuccessHandler {
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final AuthenticationException e)
            throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("login failure");
        }
        final ResultEntity<Object> loginFail = RestResult.builder()
                .withError(new BaseException("username or password not match!"))
                .withStatus(AuthEnums.LOGIN_FAIL)
                .build();
        ResponseUtil.resJson(response, loginFail);
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication)
            throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("login success");
        }
        final String sign = SignUtil
                .sign((JwtUserClaims) authentication.getDetails(), 1, DateField.HOUR);
        final ResultEntity<Object> loginSuccess = RestResult.builder()
                .withStatus(AuthEnums.LOGIN_SUCCESS)
                .withData(sign)
                .build();
        ResponseUtil.resJson(response, loginSuccess);
    }

    @Override
    public void onLogoutSuccess(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Authentication authentication)
            throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("logout success");
        }
        final ResultEntity<Object> logoutSuccess = RestResult.builder()
                .withError(new BaseException("username or password not match!"))
                .withStatus(AuthEnums.LOGOUT_SUCCESS)
                .build();
        ResponseUtil.resJson(response, logoutSuccess);
    }
}
