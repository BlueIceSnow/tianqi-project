package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthResourceController;
import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO;
import com.tianqi.auth.pojo.vo.UserLoginInfoVO;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.client.util.AuthUtil;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.business.AuthEnum;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.movie.api.IDemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资源表(TqAuthResource)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:45
 */
@RestController
@RequestMapping("tqAuthResource")
public class TqAuthResourceControllerImpl
        extends BaseControllerImpl<ITqAuthResourceService, TqAuthResourceDO>
        implements ITqAuthResourceController {

    @Autowired
    private IDemoApi demoApi;

    /**
     * 获取当前登录用户的菜单
     *
     * @return
     */
    @GetMapping("loadMenu")
    public ResultEntity<UserLoginInfoVO> loadMenu() {
        final List<String> roles = AuthUtil.roles();
        final JwtUserClaims details = AuthUtil.userClaims();
        final List<ResourceDetailDTO> resourceDetailDTOS =
                service.loadLoginUserResourceList(roles, AuthUtil.tenantId(),
                        AuthUtil.appId());
        final UserLoginInfoVO userLoginInfoVO = new UserLoginInfoVO();
        userLoginInfoVO.setUsername(details.getUsername());
        userLoginInfoVO.setId(details.getId());
        userLoginInfoVO.setState(details.getState());
        userLoginInfoVO.setType(details.getType());
        userLoginInfoVO.setOrgCode(details.getOrgCode());
        userLoginInfoVO.setOrgId(details.getOrgId());
        userLoginInfoVO.setTenantId(details.getTenantId());
        userLoginInfoVO.setMenus(resourceDetailDTOS);
        userLoginInfoVO.setAppId(details.getAppId());
        return RestResult.<UserLoginInfoVO>builder()
                .withData(userLoginInfoVO)
                .withStatus(AuthEnum.MENU_LOAD_OK)
                .ok(true)
                .build();
    }

    /**
     * 根据条件查询当前登录用户拥有的资源列表
     *
     * @param tqAuthResourceDO
     * @return
     */
    @GetMapping("listResourceByRole")
    public ResultEntity<List<TqAuthResourceDO>> listResourceByRole(
            final TqAuthResourceDO tqAuthResourceDO) {
        final List<TqAuthResourceDO> resourceDOS =
                service.listResourceByRole(tqAuthResourceDO,
                        AuthUtil.tenantId(),
                        tqAuthResourceDO.getAppId() == null ? AuthUtil.appId() :
                                tqAuthResourceDO.getAppId());
        return RestResult.<List<TqAuthResourceDO>>builder()
                .withData(resourceDOS)
                .withStatus(AuthEnum.MENU_LOAD_OK)
                .ok(true).build();
    }
}
