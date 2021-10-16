package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthRoleGroupDAO;
import com.tianqi.auth.pojo.TqAuthRoleGroupDO;
import com.tianqi.auth.service.ITqAuthRoleGroupService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色组表(TqAuthRoleGroup)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:09
 */
@Service
public class TqAuthRoleGroupServiceImpl
        extends BaseServiceImpl<ITqAuthRoleGroupDAO, TqAuthRoleGroupDO>
        implements ITqAuthRoleGroupService {

}
