package com.tianqi.auth.config.security.authentication;

import com.tianqi.common.pojo.JwtUserClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/15 10:22
 * @Description: 调用认证管理器进行认证
 */
public class JwtTokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static String APP_KEY = "appKey";
    private final static String TENANT_ID = "tenantId";

    public JwtTokenAuthenticationFilter() {
        setFilterProcessesUrl("/token");
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response)
            throws AuthenticationException {
        final JwtAuthenticationToken authenticationToken =
                new JwtAuthenticationToken(null);
        final String username = request.getParameter(USERNAME);
        final String password = request.getParameter(PASSWORD);
        final String appKey = request.getParameter(APP_KEY);
        final String tenantId = request.getParameter(TENANT_ID);
        final JwtUserClaims jwtUserClaims = new JwtUserClaims();
        jwtUserClaims.setUsername(username);
        authenticationToken.setPassword(password);
        jwtUserClaims.setAppKey(appKey);
        jwtUserClaims.setTenantId(Integer.parseInt(tenantId));
        authenticationToken.setDetails(jwtUserClaims);
        return getAuthenticationManager().authenticate(authenticationToken);
    }


}
