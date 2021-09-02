package com.tianqi.client.config.security.authorization;

import com.tianqi.client.config.security.IJwtSecurityMetaService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/19 10:37
 * @Description: 系统权限元数据
 */
public class JwtSecurityMetaDataSource implements
        FilterInvocationSecurityMetadataSource {

    private final DefaultFilterInvocationSecurityMetadataSource
            defaultFilterInvocationSecurityMetadataSource;
    private IJwtSecurityMetaService metaService;
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    public void setMetaService(final IJwtSecurityMetaService metaService) {
        this.metaService = metaService;
    }

    public JwtSecurityMetaDataSource(
            final DefaultFilterInvocationSecurityMetadataSource
                    defaultFilterInvocationSecurityMetadataSource) {
        this.defaultFilterInvocationSecurityMetadataSource =
                defaultFilterInvocationSecurityMetadataSource;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(final Object object)
            throws IllegalArgumentException {
        this.loadDataSource();
        final Collection<ConfigAttribute> attributes =
                defaultFilterInvocationSecurityMetadataSource.getAttributes(object);
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        final Iterator<Map.Entry<RequestMatcher, Collection<ConfigAttribute>>> customMap =
                this.requestMap.entrySet().iterator();
        Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry;
        do {
            if (!customMap.hasNext()) {
                return attributes;
            }
            entry = customMap.next();
        } while (!entry.getKey().matches(request));

        return entry.getValue();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        this.loadDataSource();
        final Set<ConfigAttribute> allAttributes = new HashSet<>();

        for (final Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : this.requestMap
                .entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        final Collection<ConfigAttribute> allConfigAttributes =
                defaultFilterInvocationSecurityMetadataSource.getAllConfigAttributes();
        allAttributes.addAll(allConfigAttributes);
        return allAttributes;
    }

    public void loadDataSource() {
        this.requestMap = new LinkedHashMap<>();
        final Map<String, List<ConfigAttribute>> metaAuthorities =
                metaService.loadMetaAuthorities();
        final Set<Map.Entry<String, List<ConfigAttribute>>> entries =
                metaAuthorities.entrySet();
        for (final Map.Entry<String, List<ConfigAttribute>> entry : entries) {
            this.requestMap.put(new AntPathRequestMatcher(entry.getKey()),
                    entry.getValue());
        }
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return clazz.isAssignableFrom(FilterInvocation.class);
    }
}
