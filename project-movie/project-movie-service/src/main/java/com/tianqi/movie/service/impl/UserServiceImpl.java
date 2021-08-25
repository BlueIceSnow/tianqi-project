package com.tianqi.movie.service.impl;

import com.tianqi.common.service.impl.BaseServiceImpl;
import com.tianqi.movie.dao.IUserDAO;
import com.tianqi.movie.pojo.UserDO;
import com.tianqi.movie.service.IUserService;
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
