package com.tianqi.auth.service.impl;

import com.tianqi.auth.pojo.TqAuthUserRoleGroupRelationDO;
import com.tianqi.auth.dao.ITqAuthUserRoleGroupRelationDAO;
import com.tianqi.auth.service.ITqAuthUserRoleGroupRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户角色组关联表(TqAuthUserRoleGroupRelation)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:52
 */
@Service
public class TqAuthUserRoleGroupRelationServiceImpl extends
        BaseServiceImpl<ITqAuthUserRoleGroupRelationDAO, TqAuthUserRoleGroupRelationDO>
        implements ITqAuthUserRoleGroupRelationService {

}
