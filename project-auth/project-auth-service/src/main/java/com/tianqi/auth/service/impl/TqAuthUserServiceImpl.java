package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.config.security.authorization.JwtAuthority;
import com.tianqi.auth.dao.ITqAuthUserDAO;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.auth.service.ITqAuthUserService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户表(TqAuthUser)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:44
 */
@Service
public class TqAuthUserServiceImpl extends BaseServiceImpl<ITqAuthUserDAO, TqAuthUserDO>
        implements ITqAuthUserService {
    private ITqAuthUserDAO userDAO;

    @Autowired
    public void setUserDAO(final ITqAuthUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public TqAuthUserDO loadUserInfo(final String username) {
        final QueryWrapper<TqAuthUserDO> userQueryWrap = new QueryWrapper<>();
        userQueryWrap.lambda().eq(TqAuthUserDO::getUsername, username);
        final TqAuthUserDO tqAuthUserDO = userDAO.selectOne(userQueryWrap);
        return tqAuthUserDO;
    }

    @Override
    public List<String> loadIgnoringAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public List<JwtAuthority> loadAuthorities(final String username) {
        return new ArrayList<>();
    }

    @Override
    public Map<String, List<ConfigAttribute>> loadMetaAuthorities() {
        return new LinkedHashMap<>();
    }
}
