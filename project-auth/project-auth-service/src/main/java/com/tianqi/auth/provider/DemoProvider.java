package com.tianqi.auth.provider;

import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.auth.service.ITqAuthUserService;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.result.rpc.RpcResult;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/17 18:12
 * @Description:
 */
@RestController
public class DemoProvider {
    private ITqAuthUserService userService;

    @Autowired
    public void setUserService(final ITqAuthUserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/listAll")
    public RpcResultEntity<List<TqAuthUserDO>> listAll() {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        final List<TqAuthUserDO> userDOS =
                userService.listEntity(TqAuthUserDO.builder().build()).getData()
                        .doOrDto();
        return RpcResult.<List<TqAuthUserDO>>builder().withStatus(StatusEnum.OK)
                .withResult(userDOS).build();
    }
}
