package com.tianqi.movie.controller.impl;

import com.tianqi.auth.api.IDemoApi;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.BaseEnum;
import com.tianqi.common.enums.BooleanEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import com.tianqi.movie.controller.IUserController;
import com.tianqi.movie.pojo.UserDO;
import com.tianqi.movie.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:15
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends BaseControllerImpl<IUserService, UserDO>
        implements IUserController {

    @Autowired
    private IDemoApi demoApi;
    @Autowired
    private com.tianqi.info.api.IDemoApi iDemoApi;
    @Autowired
    private HttpServletRequest request;

    @Override
    @PreAuthorize("hasRole('BLUE')")
    public ResultEntity<List<com.tianqi.info.pojo.UserDO>> testFeign() {
        log.debug("debug信息");
        final com.tianqi.info.pojo.UserDO userDO = new com.tianqi.info.pojo.UserDO();
        userDO.setDeleted(BooleanEnum.TRUE);
        final RpcResultEntity<List<com.tianqi.info.pojo.UserDO>> listResultEntity =
                iDemoApi.listAll(userDO);
        final List<com.tianqi.info.pojo.UserDO> result = listResultEntity.getResult();
        final BaseEnum status = listResultEntity.getStatus();

        return RestResult.<List<com.tianqi.info.pojo.UserDO>>builder()
                .withStatus(status).withData(result).build();
    }
}

