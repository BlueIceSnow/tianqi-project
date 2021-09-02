package com.tianqi.auth.service.impl;

import com.tianqi.auth.config.security.authentication.JwtAuthenticationToken;
import com.tianqi.auth.dao.ITqAuthUserDAO;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.auth.pojo.bo.TqAuthUserLoginBO;
import com.tianqi.auth.pojo.entity.JwtAuthority;
import com.tianqi.auth.service.ITqAuthOrgRoleRelationService;
import com.tianqi.auth.service.ITqAuthUserOrgRelationService;
import com.tianqi.auth.service.ITqAuthUserRoleGroupRelationService;
import com.tianqi.auth.service.ITqAuthUserService;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户表(TqAuthUser)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:44
 */
@Service
public class TqAuthUserServiceImpl extends BaseServiceImpl<ITqAuthUserDAO, TqAuthUserDO>
        implements ITqAuthUserService {
    private ITqAuthUserDAO userDAO;
    private ITqAuthUserOrgRelationService userOrgRelationService;
    private ITqAuthUserRoleGroupRelationService userRoleGroupRelationService;
    private ITqAuthOrgRoleRelationService orgRoleRelationService;

    @Autowired
    public void setOrgRoleRelationService(
            final ITqAuthOrgRoleRelationService orgRoleRelationService) {
        this.orgRoleRelationService = orgRoleRelationService;
    }

    @Autowired
    public void setUserRoleGroupRelationService(
            final ITqAuthUserRoleGroupRelationService userRoleGroupRelationService) {
        this.userRoleGroupRelationService = userRoleGroupRelationService;
    }

    @Autowired
    public void setUserOrgRelationService(
            final ITqAuthUserOrgRelationService userOrgRelationService) {
        this.userOrgRelationService = userOrgRelationService;
    }

    @Autowired
    public void setUserDAO(final ITqAuthUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public TqAuthUserLoginBO loadUserInfo(final String username, final Integer tenantId,
                                          final String appKey) {
        // 根据用户名及租户ID查询用户
        final TqAuthUserDO tqAuthUserDO =
                userDAO.selectUserByUsernameAndTenantId(username, tenantId);
        if (tqAuthUserDO == null) {
            throw new BaseException("user is not exist");
        }
        final Integer userId = tqAuthUserDO.getId();
        // 查询用户组织Code
        final String orgCode =
                userOrgRelationService
                        .selectUserOrgByTenantIdAndAppKey(userId, tenantId, appKey);
        // 查询用户所加入的角色组角色
        final List<String> userRoleGroupRoleList =
                userRoleGroupRelationService
                        .selectUserRoleListByUserId(userId, orgCode);
        // 查询用户所加入组织后拥有的角色
        final List<String> orgRoleList =
                orgRoleRelationService.selectOrgRoleListByOrgCode(orgCode);
        // 封装用户权限
        userRoleGroupRoleList.addAll(orgRoleList);
        final List<JwtAuthority> userAuthorities =
                userRoleGroupRoleList.stream().map(JwtAuthority::new)
                        .collect(Collectors.toList());
        // 查询用户数据权限信息

        final TqAuthUserLoginBO resultBO = TqAuthUserLoginBO.builder()
                .orgCode(orgCode)
                .authorities(userAuthorities)
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
        final JwtUserClaims details = authentication.getDetails();
        final Integer tenantId = details.getTenantId();
        final Integer userId = details.getId();
        // 此处应该写死为认证平台的 AppKey
        final String appKey = "-1";
        // 根据租户ID、租户ID、AppKey获取用户所拥有的认证平台角色


        return new LinkedHashMap<>();
    }
}
