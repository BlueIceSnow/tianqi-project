package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.ITqAuthApplicationDAO;
import com.tianqi.auth.pojo.TqAuthApplicationDO;
import com.tianqi.auth.service.ITqAuthApplicationService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 应用表(TqAuthApplication)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:25:57
 */
@Service
public class TqAuthApplicationServiceImpl
        extends BaseServiceImpl<ITqAuthApplicationDAO, TqAuthApplicationDO>
        implements ITqAuthApplicationService {
}
