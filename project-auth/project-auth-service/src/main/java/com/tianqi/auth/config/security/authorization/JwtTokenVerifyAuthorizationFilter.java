package com.tianqi.auth.config.security.authorization;

import cn.hutool.core.util.StrUtil;
import com.tianqi.auth.config.security.IJwtSecurityMetaService;
import com.tianqi.auth.config.security.authentication.JwtAuthenticationToken;
import com.tianqi.auth.config.security.hook.JwtAuthenticationEntryPoint;
import com.tianqi.auth.pojo.entity.JwtAuthority;
import com.tianqi.common.constant.SystemConstant;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.util.SignUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
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
    private IJwtSecurityMetaService securityMetaService;

    @Autowired
    public void setSecurityMetaService(
            final IJwtSecurityMetaService securityMetaService) {
        this.securityMetaService = securityMetaService;
    }

    @Autowired
    public void setEntryPoint(
            final JwtAuthenticationEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    /**
     * test.
     *
     * @param: request 请求
     * @param: response 响应
     * @param: filterChain
     * @return: void
     * @date: 2021/10/12 11:14:36
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    @Nonnull final HttpServletResponse response,
                                    @Nonnull final FilterChain filterChain)
            throws ServletException, IOException {
        final String token = request.getHeader(SystemConstant.HEADER_TOKEN);
        final List<String> ignoringAuthorities = securityMetaService.loadIgnoringAuthorities();
        final AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean isIgnoring = false;
        for (final String ignoringAuthority : ignoringAuthorities) {
            isIgnoring = antPathMatcher.match(ignoringAuthority, request.getRequestURI());
            if (isIgnoring) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        if (StrUtil.isNotEmpty(token)) {
            // 用户TOKEN存在，验证用户TOKEN
            final JwtUserClaims jwtUserClaims;
            try {
                jwtUserClaims = SignUtil.parseSign(token);
            } catch (final JwtException jwtException) {
                entryPoint.commence(request, response, null);
                return;
            }
            final List<JwtAuthority> roles =
                    jwtUserClaims.getRoles().stream()
                            .map(JwtAuthority::new)
                            .collect(Collectors.toList());
            final JwtAuthenticationToken jwtAuthenticationToken =
                    new JwtAuthenticationToken(roles);
            // 封装用户权限
            jwtAuthenticationToken.setAuthenticated(true);
            jwtAuthenticationToken.setDetails(jwtUserClaims);
            SecurityContextHolder.getContext()
                    .setAuthentication(jwtAuthenticationToken);
            filterChain.doFilter(request, response);
        } else {
            // 用户TOKEN不存在
            entryPoint.commence(request, response, null);
        }
    }
}
