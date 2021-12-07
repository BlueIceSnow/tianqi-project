package com.tianqi.client.config.security.authorization;

import com.tianqi.client.config.security.IJwtSecurityMetaService;
import com.tianqi.client.config.security.hook.JwtAuthenticationEntryPoint;
import com.tianqi.client.constant.AuthConstant;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.util.SignUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
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
    private IJwtSecurityMetaService metaService;

    @Autowired
    public void setMetaService(final IJwtSecurityMetaService metaService) {
        this.metaService = metaService;
    }

    @Autowired
    public void setEntryPoint(
            final JwtAuthenticationEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
        final List<String> ignoringAuthorities = metaService.loadIgnoringAuthorities();
        final AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean shouldFilter = false;
        for (final String ignoringAuthority : ignoringAuthorities) {
            shouldFilter = antPathMatcher.match(ignoringAuthority, request.getRequestURI());
            if (shouldFilter) {
                break;
            }
        }
        return shouldFilter;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        final String token = request.getHeader(AuthConstant.HEADER_TOKEN);
        if (!StringUtils.isEmpty(token)) {
            JwtUserClaims jwtUserClaims = null;
            try {
                jwtUserClaims = SignUtil.parseSign(token);
            } catch (final JwtException jwtException) {
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
