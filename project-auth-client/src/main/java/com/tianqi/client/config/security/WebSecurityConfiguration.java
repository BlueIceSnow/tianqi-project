package com.tianqi.client.config.security;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.tianqi.client.config.security.authorization.JwtTokenVerifyAuthorizationFilter;
import com.tianqi.client.config.security.hook.FilterExceptionProcessor;
import com.tianqi.client.config.security.hook.JwtAccessDeniedHandler;
import com.tianqi.client.config.security.hook.JwtPostProcessor;
import com.tianqi.client.config.sql.AppIdHandler;
import com.tianqi.client.config.sql.ExtendSqlInterceptor;
import com.tianqi.client.config.sql.IsDeleteHandler;
import com.tianqi.client.config.sql.OrderExtendSqlInterceptor;
import com.tianqi.client.config.sql.OrgCodeHandler;
import com.tianqi.client.config.sql.TenantIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/20 15:28
 * @Description: 权限管理配置
 */
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    public static final String SUPPORT_PREFIX = "TQ:ROLE:";
    private IJwtSecurityMetaService metaService;
    private JwtAccessDeniedHandler accessDeniedHandler;
    private AuthenticationEntryPoint authenticationEntryPoint;
    private JwtTokenVerifyAuthorizationFilter verifyAuthorizationFilter;

    @Autowired
    public void setVerifyAuthorizationFilter(
            final JwtTokenVerifyAuthorizationFilter verifyAuthorizationFilter) {
        this.verifyAuthorizationFilter = verifyAuthorizationFilter;
    }

    @Autowired
    public void setAuthenticationEntryPoint(
            final AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    public void setAccessDeniedHandler(
            final JwtAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void setMetaService(final IJwtSecurityMetaService metaService) {
        this.metaService = metaService;
    }

    /**
     * 配置权限认证相关
     *
     * @param http
     * @throws Exception
     */
    @Override

    protected void configure(final HttpSecurity http) throws Exception {
        http.formLogin().disable()
                .csrf().disable()
                .sessionManagement().disable()
                .addFilterBefore(verifyAuthorizationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
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
     * @param web
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

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        final MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(
                new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(
                new ExtendSqlInterceptor(new IsDeleteHandler()));
        interceptor.addInnerInterceptor(
                new ExtendSqlInterceptor(new OrgCodeHandler()));
        interceptor.addInnerInterceptor(
                new ExtendSqlInterceptor(new AppIdHandler()));
        interceptor.addInnerInterceptor(
                new ExtendSqlInterceptor(new TenantIdHandler()));
        interceptor.addInnerInterceptor(new OrderExtendSqlInterceptor());
        return interceptor;
    }
}