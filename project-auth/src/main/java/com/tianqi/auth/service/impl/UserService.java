package com.tianqi.auth.service.impl;

import com.tianqi.auth.pojo.UserDO;
import com.tianqi.auth.dao.IUserDAO;
import com.tianqi.auth.service.IUserService;
import com.tianqi.common.service.impl.BaseService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-04-11 17:33:45
 */
@Service
public class UserService extends BaseService<IUserDAO, UserDO> implements IUserService {

}
