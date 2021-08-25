package com.tianqi.client.config.security.authorization;

import com.tianqi.client.config.security.hook.JwtAccessDeniedHandler;
import com.tianqi.client.config.security.hook.JwtAuthenticationEntryPoint;
import com.tianqi.client.constant.SystemConstant;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.util.SignUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/22 13:58
 * @Description:
 */
@Component
public class JwtTokenVerifyAuthorizationFilter extends OncePerRequestFilter {
    private JwtAuthenticationEntryPoint entryPoint;
    private JwtAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public void setAccessDeniedHandler(
            final JwtAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void setEntryPoint(
            final JwtAuthenticationEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        final String token = request.getHeader(SystemConstant.HEADER_TOKEN);
        if (!StringUtils.isEmpty(token)) {
            JwtUserClaims jwtUserClaims = null;
            try {
                jwtUserClaims = SignUtil.parseSign(token);
            } catch (JwtException jwtException) {
                entryPoint.commence(request, response, null);
                return;
            }
            final List<JwtAuthority> roles =
                    jwtUserClaims.getRoles().stream()
                            .map(role -> new JwtAuthority(role))
                            .collect(Collectors.toList());
            final JwtAuthenticationToken jwtAuthenticationToken =
                    new JwtAuthenticationToken(roles);
            jwtAuthenticationToken.setAuthenticated(true);
            jwtAuthenticationToken.setDetails(jwtUserClaims);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
            filterChain.doFilter(request, response);
        } else {
            entryPoint.commence(request, response, null);
        }
    }
}
