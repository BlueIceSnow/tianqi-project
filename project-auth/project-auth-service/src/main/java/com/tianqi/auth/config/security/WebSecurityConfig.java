package com.tianqi.auth.config.security;

import com.tianqi.auth.config.security.authentication.JwtAuthenticationProvider;
import com.tianqi.auth.config.security.hook.FilterExceptionProcessor;
import com.tianqi.auth.config.security.hook.JwtAccessDeniedHandler;
import com.tianqi.auth.config.security.hook.JwtAuthenticationEntryPoint;
import com.tianqi.auth.config.security.hook.JwtLoginHandler;
import com.tianqi.auth.config.security.hook.JwtPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/20 15:28
 * @Description: 权限管理配置
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String SUPPORT_PREFIX = "TQ:ROLE:";
    private JwtLoginHandler loginHandler;
    private JwtAuthenticationProvider authenticationProvider;
    private JwtAccessDeniedHandler accessDeniedHandler;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private IJwtSecurityMetaService metaService;

    @Autowired
    public void setLoginHandler(final JwtLoginHandler loginHandler) {
        this.loginHandler = loginHandler;
    }

    @Autowired
    public void setAuthenticationProvider(
            final JwtAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    public void setAccessDeniedHandler(
            final JwtAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void setAuthenticationEntryPoint(
            final JwtAuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    public void setMetaService(final IJwtSecurityMetaService metaService) {
        this.metaService = metaService;
    }

    /**
     * 配置权限认证相关
     *
     * @param http http安全配置
     * @throws Exception
     */
    @Override

    protected void configure(final HttpSecurity http) throws Exception {
        http.formLogin().disable()
                .csrf().disable()
                .sessionManagement().disable()
                .apply(new JwtConfigurer<>())
                .failureHandler(loginHandler)
                .successHandler(loginHandler)
                .and().logout()
                .logoutSuccessHandler(loginHandler)
                .and().authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .mvcMatchers("/user/page").hasRole("ADMIN")
                .anyRequest().authenticated()
                .withObjectPostProcessor(new JwtPostProcessor())
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().addFilterBefore(new FilterExceptionProcessor(),
                UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 配置Web安全，会在SecurityFilterProxy中生成一个新的过滤器链
     *
     * @param web Web安全配置类
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        // 可匿名访问资源
        final List<String> ignoringAuthorities = metaService
                .loadIgnoringAuthorities();
        web.ignoring().antMatchers(ignoringAuthorities.toArray(new String[] {}));

        final DefaultWebSecurityExpressionHandler expressionHandler =
                (DefaultWebSecurityExpressionHandler) web.getExpressionHandler();
        expressionHandler.setDefaultRolePrefix(SUPPORT_PREFIX);
    }

    /**
     * 密码加密方式
     *
     * @return 使用代理加密器，可以适应不用加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}