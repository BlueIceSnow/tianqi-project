package com.tianqi.movie.controller;

import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.movie.pojo.UserDO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * (User)表控制层
 *
 * @Author yuantianqi
 * @since 2021-04-11 18:37:13
 */
public interface IUserController extends IBaseController<UserDO> {

    /**
     * 测试Feign调用
     *
     * @return
     */
    @GetMapping("testFeign")
    ResultEntity<List<com.tianqi.info.pojo.UserDO>> testFeign();
}
