package com.tianqi.client.config.security.authorization;

import com.tianqi.client.constant.AuthConstant;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/18 09:57
 * @Description: 角色投票
 */
public class JwtRoleVoter implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(final ConfigAttribute attribute) {
        return attribute != null &&
                attribute.getClass().isAssignableFrom(JwtConfigAttribute.class) &&
                attribute.getAttribute().startsWith(AuthConstant.ROLE_AUTHORITY_PREFIX);
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(final Authentication authentication, final Object object,
                    final Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        int result = ACCESS_ABSTAIN;
        final Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();

        for (final ConfigAttribute attribute : attributes) {
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;

                // Attempt to find a matching granted authority
                for (final GrantedAuthority authority : authorities) {
                    if (attribute.getAttribute().equals(authority.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }

        return result;
    }
}
