package com.tianqi.auth.config.security;

import com.alibaba.nacos.common.utils.HttpMethod;
import com.tianqi.auth.config.security.authentication.JwtTokenAuthenticationFilter;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/15 15:48
 * @Description:
 */
public class JwtConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractAuthenticationFilterConfigurer<H, JwtConfigurer<H>, JwtTokenAuthenticationFilter> {

    public JwtConfigurer() {
        super(new JwtTokenAuthenticationFilter(), null);
    }


    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(
            final String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, HttpMethod.GET);
    }

    @Override
    public JwtConfigurer<H> loginProcessingUrl(final String loginProcessingUrl) {
        return super.loginProcessingUrl("/doLogin");
    }
}
