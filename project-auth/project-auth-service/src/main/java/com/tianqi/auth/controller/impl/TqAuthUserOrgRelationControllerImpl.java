package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthUserOrgRelationController;
import com.tianqi.auth.pojo.TqAuthUserOrgRelationDO;
import com.tianqi.auth.service.ITqAuthUserOrgRelationService;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户组织关联表(TqAuthUserOrgRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-26 15:33:19
 */
@RestController
@RequestMapping("tqAuthUserOrgRelation")
public class TqAuthUserOrgRelationControllerImpl
        extends
        BaseControllerImpl<ITqAuthUserOrgRelationService, TqAuthUserOrgRelationDO>
        implements ITqAuthUserOrgRelationController {

}
