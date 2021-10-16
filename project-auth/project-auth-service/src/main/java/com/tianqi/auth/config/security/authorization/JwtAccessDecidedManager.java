package com.tianqi.auth.config.security.authorization;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/18 09:54
 * @Description: 授权管理器
 */
public class JwtAccessDecidedManager extends AbstractAccessDecisionManager {


    public JwtAccessDecidedManager(
            final List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }
    

    @Override
    public void decide(final Authentication authentication, final Object object,
                       final Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        int deny = 0;
        for (final AccessDecisionVoter voter : getDecisionVoters()) {
            final int result =
                    voter.vote(authentication, object, configAttributes);

            switch (result) {
                case AccessDecisionVoter.ACCESS_GRANTED:
                    return;

                case AccessDecisionVoter.ACCESS_DENIED:
                    deny++;

                    break;

                default:
                    break;
            }
        }
        if (deny > 0) {
            throw new AccessDeniedException(messages.getMessage(
                    "AbstractAccessDecisionManager.accessDenied",
                    "Access is denied"));
        }
        // 允许进行弃权，如果都弃权则进入方法鉴权
        if (configAttributes.stream()
                .anyMatch(config -> config.getAttribute()
                        .contains("TQ:METHOD"))) {
            setAllowIfAllAbstainDecisions(true);
        } else {
            setAllowIfAllAbstainDecisions(false);
        }
        checkAllowIfAllAbstainDecisions();
    }


}
