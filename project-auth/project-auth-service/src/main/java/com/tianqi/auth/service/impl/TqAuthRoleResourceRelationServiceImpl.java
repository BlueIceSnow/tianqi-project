package com.tianqi.auth.service.impl;

import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.dao.ITqAuthRoleResourceRelationDAO;
import com.tianqi.auth.service.ITqAuthRoleResourceRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色资源关联表(TqAuthRoleResourceRelation)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:18
 */
@Service
public class TqAuthRoleResourceRelationServiceImpl extends
        BaseServiceImpl<ITqAuthRoleResourceRelationDAO, TqAuthRoleResourceRelationDO>
        implements ITqAuthRoleResourceRelationService {

}
