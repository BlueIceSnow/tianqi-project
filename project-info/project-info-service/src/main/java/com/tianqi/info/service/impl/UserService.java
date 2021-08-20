package com.tianqi.info.service.impl;

import com.tianqi.info.pojo.UserDO;
import com.tianqi.info.dao.IUserDAO;
import com.tianqi.info.service.IUserService;
import com.tianqi.common.service.impl.BaseService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:12
 */
@Service
public class UserService extends BaseService<IUserDAO, UserDO> implements IUserService {

}
