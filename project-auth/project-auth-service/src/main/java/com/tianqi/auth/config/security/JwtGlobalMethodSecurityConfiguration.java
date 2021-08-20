package com.tianqi.auth.config.security;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.annotation.Jsr250Voter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/19 19:19
 * @Description: 进行方法级安全进行控制
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, mode = AdviceMode.PROXY)
public class JwtGlobalMethodSecurityConfiguration
        extends GlobalMethodSecurityConfiguration {

    public static final String SUPPORT_PREFIX = "TQ:METHOD:";

    private AnnotationAttributes enableMethodSecurity;

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        if (prePostEnabled()) {
            ExpressionBasedPreInvocationAdvice expressionAdvice =
                    new ExpressionBasedPreInvocationAdvice();
            expressionAdvice.setExpressionHandler(getExpressionHandler());
            decisionVoters
                    .add(new PreInvocationAuthorizationAdviceVoter(expressionAdvice));
        }
        if (jsr250Enabled()) {
            decisionVoters.add(new Jsr250Voter());
        }

        ((DefaultMethodSecurityExpressionHandler) this.getExpressionHandler())
                .setDefaultRolePrefix(SUPPORT_PREFIX);
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix(SUPPORT_PREFIX);
        decisionVoters.add(roleVoter);
        decisionVoters.add(new AuthenticatedVoter());
        return new AffirmativeBased(decisionVoters);
    }

    private boolean prePostEnabled() {
        return enableMethodSecurity().getBoolean("prePostEnabled");
    }

    private boolean securedEnabled() {
        return enableMethodSecurity().getBoolean("securedEnabled");
    }

    private boolean jsr250Enabled() {
        return enableMethodSecurity().getBoolean("jsr250Enabled");
    }


    private AnnotationAttributes enableMethodSecurity() {
        if (enableMethodSecurity == null) {
            // if it is null look at this instance (i.e. a subclass was used)
            EnableGlobalMethodSecurity methodSecurityAnnotation = AnnotationUtils
                    .findAnnotation(getClass(), EnableGlobalMethodSecurity.class);
            Assert.notNull(methodSecurityAnnotation,
                    () -> EnableGlobalMethodSecurity.class.getName() + " is required");
            Map<String, Object> methodSecurityAttrs = AnnotationUtils
                    .getAnnotationAttributes(methodSecurityAnnotation);
            this.enableMethodSecurity = AnnotationAttributes.fromMap(methodSecurityAttrs);
        }
        return this.enableMethodSecurity;
    }
}
