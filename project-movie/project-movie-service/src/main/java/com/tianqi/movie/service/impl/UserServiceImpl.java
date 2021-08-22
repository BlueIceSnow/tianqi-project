package com.tianqi.movie.service.impl;

import com.tianqi.common.service.impl.BaseServiceImpl;
import com.tianqi.movie.dao.IUserDAO;
import com.tianqi.movie.pojo.UserDO;
import com.tianqi.movie.service.IUserService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:12
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<IUserDAO, UserDO>
        implements IUserService {

    @Override
    public List<String> loadIgnoringAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, List<ConfigAttribute>> loadMetaAuthorities() {
        return new LinkedHashMap<>();
    }
}
