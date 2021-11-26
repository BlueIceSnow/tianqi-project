package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.common.dao.IBaseDAO;

import java.util.List;

/**
 * 组织架构表(TqAuthOrg)表数据库访问层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:24
 */
public interface ITqAuthOrgDAO extends IBaseDAO<TqAuthOrgDO> {

    /**
     * 根据租户、应用及当前登录用户所属组织查询组织树
     *
     * @param tenantId
     * @param appId
     * @param orgCode
     * @return List<TqAuthOrgDO>
     */
    List<TqAuthOrgDO> listOrgByAppIdAndTenantIdAndCurrentLoginUserOrg(Integer tenantId,
                                                                      Integer appId,
                                                                      String orgCode);

    /**
     * 根据租户、应用查询组织树
     *
     * @param tenantId
     * @param appId
     * @return List<TqAuthOrgDO>
     */
    List<TqAuthOrgDO> listOrgByAppIdAndTenantId(Integer tenantId, Integer appId);
}

