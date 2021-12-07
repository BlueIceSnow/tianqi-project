package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthRoleGroupDO;
import com.tianqi.common.service.IBaseService;

/**
 * 角色组表(TqAuthRoleGroup)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:09
 */
public interface ITqAuthRoleGroupService
        extends IBaseService<TqAuthRoleGroupDO> {
    /**
     * 删除包含角色字段
     *
     * @param ids 删除角色IDS
     */
    void removeRelationRoleField(String[] ids);
}
