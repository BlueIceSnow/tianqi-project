package com.tianqi.auth.service.impl;

import com.tianqi.auth.pojo.TqAuthDataPermissionDO;
import com.tianqi.auth.dao.ITqAuthDataPermissionDAO;
import com.tianqi.auth.service.ITqAuthDataPermissionService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 数据权限表(TqAuthDataPermission)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:13
 */
@Service
public class TqAuthDataPermissionServiceImpl
        extends BaseServiceImpl<ITqAuthDataPermissionDAO, TqAuthDataPermissionDO>
        implements ITqAuthDataPermissionService {

}
