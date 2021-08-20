package com.tianqi.auth.config.security.authentication;

import com.tianqi.auth.pojo.TqUserDO;
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

    public JwtTokenAuthenticationFilter() {
        setFilterProcessesUrl("/doLogin");
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response)
            throws AuthenticationException {
        final JwtAuthenticationToken authenticationToken =
                new JwtAuthenticationToken(null);
        final String username = request.getParameter(USERNAME);
        final String password = request.getParameter(PASSWORD);
        final TqUserDO userDO = new TqUserDO();
        userDO.setUsername(username);
        userDO.setPassword(password);
        authenticationToken.setDetails(userDO);
        return getAuthenticationManager().authenticate(authenticationToken);
    }


}
