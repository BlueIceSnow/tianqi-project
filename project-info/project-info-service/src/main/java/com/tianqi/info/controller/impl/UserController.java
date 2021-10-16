package com.tianqi.info.controller.impl;

import com.tianqi.auth.api.IDemoApi;
import com.tianqi.client.controller.impl.BaseControllerImpl;
import com.tianqi.common.enums.BaseEnum;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;
import com.tianqi.info.controller.IUserController;
import com.tianqi.info.pojo.UserDO;
import com.tianqi.info.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (User)表控制层
 *
 * @Author yuantianqi
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
    private HttpServletRequest request;

    @Override
    public ResultEntity<List<com.tianqi.auth.pojo.TqAuthUserDO>> testFeign() {
        log.debug("debug信息");
        final RpcResultEntity<List<com.tianqi.auth.pojo.TqAuthUserDO>> rpcResultEntity =
                demoApi.listAll();
        final List<com.tianqi.auth.pojo.TqAuthUserDO> result = rpcResultEntity.getResult();
        final BaseEnum status = rpcResultEntity.getStatus();

        return RestResult.<List<com.tianqi.auth.pojo.TqAuthUserDO>>builder()
                .withStatus(StatusEnum.OK).withData(result).build();
    }
}
