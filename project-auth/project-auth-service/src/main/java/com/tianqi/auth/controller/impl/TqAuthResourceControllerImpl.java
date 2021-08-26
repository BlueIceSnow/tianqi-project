package com.tianqi.auth.controller.impl;

import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.auth.controller.ITqAuthResourceController;
import org.springframework.web.bind.annotation.*;

/**
 * 资源表(TqAuthResource)表控制层
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:45
 */
@RestController
@RequestMapping("tqAuthResource")
public class TqAuthResourceControllerImpl
        extends BaseControllerImpl<ITqAuthResourceService, TqAuthResourceDO>
        implements ITqAuthResourceController {

}
