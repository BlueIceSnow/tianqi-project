package com.tianqi.auth.config.security.authorization;

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
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/22 13:58
 * @Description:
 */
@Component
public class JwtTokenVerifyAuthorizationFilter extends OncePerRequestFilter {
    private JwtAuthenticationEntryPoint entryPoint;
    public static final String ADDR = "";

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
        if (!StringUtils.isEmpty(token)) {
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
            jwtAuthenticationToken.setAuthenticated(true);
            jwtAuthenticationToken.setDetails(jwtUserClaims);
            SecurityContextHolder.getContext()
                    .setAuthentication(jwtAuthenticationToken);
            filterChain.doFilter(request, response);
        } else {
            entryPoint.commence(request, response, null);
        }
        final String test = null;
        final String[] split = test.split(",");
    }

    /**
     * test jsr305.
     *
     * @param: test
     * @return: void
     * @date: 2021/10/12 15:01:26
     */
    public static void test(@Nonnull final String test) {
        // TODO ceshi jsr305
        System.out.println(test);
        final File file = new File("/test/add.txt");
        if (file.exists()) {
            System.out.println(file);
        }
        final Vector vector = new Vector();
    }

    public static List<String> main(final String[] args) {
        final List<String> objects = null;
        if (System.getenv().equals(1)) {
            return new ArrayList<>();
        }

        return objects;
    }
}
