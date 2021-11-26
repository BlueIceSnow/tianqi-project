package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.common.dao.IBaseDAO;

/**
 * 用户表(TqAuthUser)表数据库访问层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:44
 */
public interface ITqAuthUserDAO extends IBaseDAO<TqAuthUserDO> {

    /**
     * 根据用户名及租户ID查询用户信息
     *
     * @param username 用户名
     * @return
     */
    TqAuthUserDO selectUserByUsernameAndTenantId(String username);
}

