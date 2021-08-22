package com.tianqi.client.config.security.hook;

import com.tianqi.client.config.security.IJwtSecurityMetaService;
import com.tianqi.client.config.security.authorization.JwtAccessDecidedManager;
import com.tianqi.client.config.security.authorization.JwtRoleVoter;
import com.tianqi.client.config.security.authorization.JwtSecurityInterceptorFilter;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/18 10:09
 * @Description: 对SpringSecurity的对象进行包装处理
 */
@Slf4j
public class JwtPostProcessor
        implements ObjectPostProcessor<Object> {
    public static final String SUPPORT_PREFIX = "TQ:ROLE:";
    private AccessDecisionManager accessDecisionManager;
    private JwtSecurityInterceptorFilter securityInterceptorFilter;

    @Override
    public <O> O postProcess(final O object) {
        if (object instanceof AffirmativeBased) {
            final List<AccessDecisionVoter<?>> decisionVoters =
                    ((AffirmativeBased) object).getDecisionVoters();
            decisionVoters.add(new JwtRoleVoter());
            return (O) accessDecisionManager(decisionVoters);
        }
        if (object instanceof AbstractSecurityInterceptor) {
            final FilterSecurityInterceptor filterSecurityInterceptor =
                    (FilterSecurityInterceptor) object;
            return (O) securityInterceptor(
                    filterSecurityInterceptor.getSecurityMetadataSource());
        }
        if (object instanceof DefaultWebSecurityExpressionHandler) {
            ((DefaultWebSecurityExpressionHandler) object)
                    .setDefaultRolePrefix(SUPPORT_PREFIX);
        }
        return object;
    }

    public AccessDecisionManager accessDecisionManager(
            List<AccessDecisionVoter<?>> voters) {
        if (this.accessDecisionManager == null) {
            this.accessDecisionManager =
                    new JwtAccessDecidedManager(voters);
        }
        return this.accessDecisionManager;
    }

    public AbstractSecurityInterceptor securityInterceptor(
            SecurityMetadataSource securityMetadataSource) {
        final IJwtSecurityMetaService metaService;
        try {
            metaService = SpringUtil.getBean(
                    IJwtSecurityMetaService.class);
        } catch (Exception exception) {
            log.error("meta service not configuration");
            throw new BaseException("meta service not configuration");
        }
        if (securityInterceptorFilter == null) {
            this.securityInterceptorFilter =
                    new JwtSecurityInterceptorFilter(this.accessDecisionManager,
                            securityMetadataSource, metaService);
        }
        return this.securityInterceptorFilter;
    }
}
