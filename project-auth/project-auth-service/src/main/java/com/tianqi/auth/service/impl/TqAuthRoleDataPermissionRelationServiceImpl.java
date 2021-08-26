package com.tianqi.auth.service.impl;

import com.tianqi.auth.pojo.TqAuthRoleDataPermissionRelationDO;
import com.tianqi.auth.dao.ITqAuthRoleDataPermissionRelationDAO;
import com.tianqi.auth.service.ITqAuthRoleDataPermissionRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色数据权限关联表(TqAuthRoleDataPermissionRelation)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:01
 */
@Service
public class TqAuthRoleDataPermissionRelationServiceImpl extends
        BaseServiceImpl<ITqAuthRoleDataPermissionRelationDAO, TqAuthRoleDataPermissionRelationDO>
        implements ITqAuthRoleDataPermissionRelationService {

}
