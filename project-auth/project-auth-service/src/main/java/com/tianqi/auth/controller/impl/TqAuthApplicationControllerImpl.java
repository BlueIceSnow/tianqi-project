package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthApplicationController;
import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.service.ITqAuthApplicationService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用表(TqAuthApplication)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:04
 */
@RestController
@RequestMapping("tqAuthApplication")
public class TqAuthApplicationControllerImpl
        extends
        BaseControllerImpl<ITqAuthApplicationService, TqAuthApplicationDO>
        implements ITqAuthApplicationController {


}
