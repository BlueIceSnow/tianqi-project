package com.tianqi.auth.controller;

import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;

import java.util.List;

/**
 * 组织架构表(TqAuthOrg)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:24
 */
public interface ITqAuthOrgController extends IBaseController<TqAuthOrgDO> {
    /**
     * 根据当前登录用户及APPID查询组织列表
     *
     * @param tqAuthOrgDO 查询参数
     * @return 符合条件的组织列表
     */
    ResultEntity<List<TqAuthOrgDO>> listOrgByLoginUser(final TqAuthOrgDO tqAuthOrgDO);

    /**
     * 根据TenantId及AppId查询组织树
     *
     * @param tqAuthOrgDO 查询参数
     * @return 符合条件的组织列表
     */
    ResultEntity<List<TqAuthOrgDO>> listOrgByTenantIdAndAppId(final TqAuthOrgDO tqAuthOrgDO);

}
