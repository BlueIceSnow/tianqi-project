package com.tianqi.auth.service.impl;

import com.tianqi.auth.pojo.TqAuthOrgDO;
import com.tianqi.auth.dao.ITqAuthOrgDAO;
import com.tianqi.auth.service.ITqAuthOrgService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 组织架构表(TqAuthOrg)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:26:24
 */
@Service
public class TqAuthOrgServiceImpl extends BaseServiceImpl<ITqAuthOrgDAO, TqAuthOrgDO>
        implements ITqAuthOrgService {

}
