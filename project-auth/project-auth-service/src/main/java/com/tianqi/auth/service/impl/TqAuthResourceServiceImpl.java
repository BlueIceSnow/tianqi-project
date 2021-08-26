package com.tianqi.auth.service.impl;

import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.dao.ITqAuthResourceDAO;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 资源表(TqAuthResource)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:45
 */
@Service
public class TqAuthResourceServiceImpl
        extends BaseServiceImpl<ITqAuthResourceDAO, TqAuthResourceDO>
        implements ITqAuthResourceService {

}
