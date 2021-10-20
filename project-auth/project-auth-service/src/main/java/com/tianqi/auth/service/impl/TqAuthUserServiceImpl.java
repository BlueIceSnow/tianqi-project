package com.tianqi.auth.service.impl;

import com.tianqi.auth.config.security.authentication.JwtAuthenticationToken;
import com.tianqi.auth.config.security.authorization.JwtConfigAttribute;
import com.tianqi.auth.dao.ITqAuthUserDAO;
import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.auth.pojo.bo.TqAuthUserLoginBO;
import com.tianqi.auth.pojo.entity.JwtAuthority;
import com.tianqi.auth.service.ITqAuthOrgService;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.auth.service.ITqAuthRoleService;
import com.tianqi.auth.service.ITqAuthUserService;
import com.tianqi.common.constant.SystemConstant;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户表(TqAuthUser)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:44
 */
@Service
public class TqAuthUserServiceImpl
        extends BaseServiceImpl<ITqAuthUserDAO, TqAuthUserDO>
        implements ITqAuthUserService {
    private ITqAuthUserDAO userDAO;
    private ITqAuthOrgService orgService;
    private ITqAuthRoleService roleService;
    private ITqAuthRoleResourceRelationService roleResourceRelationService;
    private RedisTemplate<String, List<JwtConfigAttribute>> redisTemplate;


    @Autowired
    public void setRedisTemplate(
            final RedisTemplate<String, List<JwtConfigAttribute>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setRoleResourceRelationService(
            final ITqAuthRoleResourceRelationService roleResourceRelationService) {
        this.roleResourceRelationService = roleResourceRelationService;
    }

    @Autowired
    public void setUserDAO(final ITqAuthUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setOrgService(final ITqAuthOrgService orgService) {
        this.orgService = orgService;
    }

    @Autowired
    public void setRoleService(final ITqAuthRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public TqAuthUserLoginBO loadUserInfo(final String username,
                                          final Integer tenantId,
                                          final String appKey) {
        // 根据用户名及租户ID查询用户
        final TqAuthUserDO tqAuthUserDO =
                userDAO.selectUserByUsernameAndTenantId(username, tenantId);
        if (tqAuthUserDO == null) {
            throw new BaseException("user is not exist");
        }
        final Integer userId = tqAuthUserDO.getId();
        // 查询用户组织Code
        final TqAuthOrgDO userOrg =
                orgService.selectUserOrgByUserIdAndTenantIdAndAppKey(userId,
                        tenantId,
                        appKey);
        if (userOrg == null) {
            throw new BaseException("user not allocation org");
        }
        final Integer orgId = userOrg.getId();
        final String orgCode = userOrg.getOrgCode();
        final Integer appId = userOrg.getAppId();
        final Set<String> userRoleList =
                roleService.selectRoleListByCondition(userId, orgId, appId);
        // 封装用户权限
        final List<JwtAuthority> userAuthorities =
                userRoleList.stream().map(JwtAuthority::new)
                        .collect(Collectors.toList());
        // 根据角色，检索每个角色对应的数据权限信息
        final List<Integer> dataPermissionIds =
                roleService.selectDataPermissionByRoleList(userRoleList);

        // 封装结果
        final TqAuthUserLoginBO resultBO = TqAuthUserLoginBO.builder()
                .userId(userId)
                .orgCode(orgCode)
                .appId(appId)
                .appKey(appKey)
                .orgId(orgId)
                .authorities(userAuthorities)
                .dataPermissions(dataPermissionIds)
                .build();
        BeanUtils.copyProperties(tqAuthUserDO, resultBO);
        return resultBO;
    }

    @Override
    public List<String> loadIgnoringAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, List<ConfigAttribute>> loadMetaAuthorities() {
        // 根据当前登录的用户所属租户及应用，获取该租户下的路径与角色映射
        final JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        Integer appId = null;
        Integer tenantId = null;
        if (authentication != null) {
            final JwtUserClaims details = authentication.getDetails();
            appId = details.getAppId();
            tenantId = details.getTenantId();
            return redisTemplate.<String, List<ConfigAttribute>>opsForHash()
                    .entries(SystemConstant.REDIS_PREFIX + SystemConstant.REDIS_AUTH_PREFIX +
                            SystemConstant.REDIS_TENANT_PREFIX + tenantId +
                            SystemConstant.REDIS_APP_PREFIX + appId);
        }
        // 根据用户ID、AppKey获取用户所拥有的认证平台角色
        final Map<String, List<String>> roleResources =
                roleResourceRelationService
                        .selectResourceRoleMapping(tenantId, appId);
        final Map<String, List<ConfigAttribute>> result = new HashMap<>();
        final Map<String, Map<String, List<ConfigAttribute>>> group = new HashMap<>();
        for (final Map.Entry<String, List<String>> entry : roleResources.entrySet()) {
            result.put(entry.getKey().split(SystemConstant.REDIS_SPLIT)[0],
                    entry.getValue().stream().map(JwtConfigAttribute::new)
                            .collect(Collectors.toList()));
            final Map<String, List<ConfigAttribute>> stringListMap =
                    group.get(entry.getKey().split(SystemConstant.REDIS_SPLIT)[1] +
                            SystemConstant.REDIS_SPLIT +
                            entry.getKey().split(SystemConstant.REDIS_SPLIT)[2]);
            if (stringListMap == null) {
                final Map<String, List<ConfigAttribute>> mapping = new HashMap<>();
                mapping.put(entry.getKey().split(SystemConstant.REDIS_SPLIT)[0],
                        entry.getValue().stream().map(JwtConfigAttribute::new)
                                .collect(Collectors.toList()));
                group.put(entry.getKey().split(SystemConstant.REDIS_SPLIT)[1] +
                                SystemConstant.REDIS_SPLIT +
                                entry.getKey().split(SystemConstant.REDIS_SPLIT)[2],
                        mapping);
            } else {
                stringListMap
                        .put(entry.getKey().split(SystemConstant.REDIS_SPLIT)[0],
                                entry.getValue().stream().map(JwtConfigAttribute::new)
                                        .collect(Collectors.toList()));
            }
        }
        // 将权限数据放入Redis中
        for (final Map.Entry<String, Map<String, List<ConfigAttribute>>> stringMapEntry : group
                .entrySet()) {
            final String key = stringMapEntry.getKey();
            redisTemplate.<String, List<ConfigAttribute>>opsForHash()
                    .putAll(SystemConstant.REDIS_PREFIX + SystemConstant.REDIS_AUTH_PREFIX +
                                    SystemConstant.REDIS_TENANT_PREFIX + key.split(
                            SystemConstant.REDIS_SPLIT)[0] + SystemConstant.REDIS_APP_PREFIX +
                                    key.split(SystemConstant.REDIS_SPLIT)[1],
                            stringMapEntry.getValue());
        }
        return result;
    }
}
