package com.tianqi.auth.controller.impl;

import com.tianqi.auth.controller.ITqAuthOrgController;
import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.service.ITqAuthOrgService;
import com.tianqi.client.util.AuthUtil;
import com.tianqi.common.controller.impl.BaseControllerImpl;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    @GetMapping("listOrgByLoginUser")
    public ResultEntity<List<TqAuthOrgDO>> listOrgByLoginUser(final TqAuthOrgDO tqAuthOrgDO) {
        // 获取当前登录用户所属租户
        return service.listOrgByAppIdAndTenantIdAndCurrentLoginUserOrg(AuthUtil.tenantId(),
                tqAuthOrgDO.getAppId(), AuthUtil.userClaims().getOrgCode());
    }

    @GetMapping("listOrg")
    @Override
    public ResultEntity<List<TqAuthOrgDO>> listOrgByTenantIdAndAppId(
            final TqAuthOrgDO tqAuthOrgDO) {
        return service.listOrgByTenantIdAndAppId(tqAuthOrgDO.getTenantId(), tqAuthOrgDO.getAppId());
    }
}
