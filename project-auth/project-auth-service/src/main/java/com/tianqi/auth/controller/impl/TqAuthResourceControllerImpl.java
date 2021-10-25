package com.tianqi.auth.controller.impl;

import com.tianqi.auth.config.security.authentication.JwtAuthenticationToken;
import com.tianqi.auth.controller.ITqAuthResourceController;
import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.vo.UserLoginInfoVO;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.AuthEnum;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("loadMenu")
    public ResultEntity<UserLoginInfoVO> resourceList() {
        final JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        final JwtUserClaims details = authentication.getDetails();
        final List<String> roles = details.getRoles();
        final UserLoginInfoVO userLoginInfoVO =
                service.loadLoginUserInfoAndResourceList(roles, details.getTenantId(),
                        details.getAppId());
        return RestResult.<UserLoginInfoVO>builder()
                .withData(userLoginInfoVO)
                .withStatus(AuthEnum.MENU_LOAD_OK)
                .ok(true)
                .build();
    }
}
