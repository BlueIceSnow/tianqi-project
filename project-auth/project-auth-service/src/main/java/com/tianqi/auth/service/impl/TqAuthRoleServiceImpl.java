package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthRoleDAO;
import com.tianqi.auth.pojo.TqAuthRoleDO;
import com.tianqi.auth.service.ITqAuthRoleService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色表(TqAuthRole)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:54
 */
@Service
public class TqAuthRoleServiceImpl extends BaseServiceImpl<ITqAuthRoleDAO, TqAuthRoleDO>
        implements ITqAuthRoleService {

}
