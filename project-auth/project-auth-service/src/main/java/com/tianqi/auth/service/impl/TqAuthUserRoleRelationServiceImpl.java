package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthUserRoleRelationDAO;
import com.tianqi.auth.pojo.TqAuthUserRoleRelationDO;
import com.tianqi.auth.service.ITqAuthUserRoleRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户角色组关联表(TqAuthUserRoleRelation)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-31 11:08:04
 */
@Service
public class TqAuthUserRoleRelationServiceImpl
        extends BaseServiceImpl<ITqAuthUserRoleRelationDAO, TqAuthUserRoleRelationDO>
        implements ITqAuthUserRoleRelationService {

}
