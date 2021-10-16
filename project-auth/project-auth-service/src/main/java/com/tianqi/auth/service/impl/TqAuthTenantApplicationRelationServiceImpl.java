package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthTenantApplicationRelationDAO;
import com.tianqi.auth.pojo.TqAuthTenantApplicationRelationDO;
import com.tianqi.auth.service.ITqAuthTenantApplicationRelationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 租户应用关联表(TqAuthTenantApplicationRelation)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:37
 */
@Service
public class TqAuthTenantApplicationRelationServiceImpl extends
        BaseServiceImpl<ITqAuthTenantApplicationRelationDAO, TqAuthTenantApplicationRelationDO>
        implements ITqAuthTenantApplicationRelationService {

}
