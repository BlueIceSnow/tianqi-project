package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthOrgController;
import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.service.ITqAuthOrgService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织架构表(TqAuthOrg)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:24
 */
@RestController
@RequestMapping("tqAuthOrg")
public class TqAuthOrgControllerImpl
        extends BaseControllerImpl<ITqAuthOrgService, TqAuthOrgDO>
        implements ITqAuthOrgController {

}
