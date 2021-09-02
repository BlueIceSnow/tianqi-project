package com.tianqi.info.service.impl;

import com.tianqi.common.service.impl.BaseServiceImpl;
import com.tianqi.info.dao.IUserDAO;
import com.tianqi.info.pojo.UserDO;
import com.tianqi.info.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:12
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<IUserDAO, UserDO>
        implements IUserService {

}
