package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.config.security.authentication.JwtAuthenticationToken;
import com.tianqi.auth.dao.ITqAuthResourceDAO;
import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO;
import com.tianqi.auth.pojo.vo.UserLoginInfoVO;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.common.enums.ResourceTypeEnum;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源表(TqAuthResource)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:45
 */
@Service
public class TqAuthResourceServiceImpl
        extends BaseServiceImpl<ITqAuthResourceDAO, TqAuthResourceDO>
        implements ITqAuthResourceService {

    /**
     * 根据应用ID及类型查询资源类表
     *
     * @param appId 应用ID
     * @param type  类型
     * @return
     */
    @Override
    public List<TqAuthResourceDO> selectResourceListByAppId(final Integer appId,
                                                            final ResourceTypeEnum type) {
        final QueryWrapper<TqAuthResourceDO> resourceQuery =
                new QueryWrapper<>();
        resourceQuery.lambda().eq(TqAuthResourceDO::getAppId, appId);
        resourceQuery.lambda().eq(TqAuthResourceDO::getType, type);
        return dao.selectList(resourceQuery);
    }

    /**
     * 根据当前登录用户信息获取用户拥有资源列表.
     *
     * @param roles
     * @param appId
     * @param tenantId
     * @return: java.util.List<com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO>
     * @date: 2021/10/19 19:56:29
     */
    @Override
    public UserLoginInfoVO loadLoginUserInfoAndResourceList(
            final List<String> roles, final Integer tenantId, final Integer appId) {
        final JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        final List<ResourceDetailDTO> resourceDetailDTOS =
                dao.selectMenuResource(roles, tenantId, appId);
        final JwtUserClaims details = authentication.getDetails();
        final UserLoginInfoVO userLoginInfoVO = new UserLoginInfoVO();
        userLoginInfoVO.setUsername(details.getUsername());
        userLoginInfoVO.setId(details.getId());
        userLoginInfoVO.setState(details.getState());
        userLoginInfoVO.setType(details.getType());
        userLoginInfoVO.setMenus(resourceDetailDTOS);
        userLoginInfoVO.setOrgCode(details.getOrgCode());
        userLoginInfoVO.setOrgId(details.getOrgId());
        return userLoginInfoVO;
    }
}
