package com.tianqi.auth.service.impl;

import com.tianqi.auth.dao.IUserDAO;
import com.tianqi.auth.pojo.UserDO;
import com.tianqi.auth.service.IUserService;
import com.tianqi.common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-04-11 17:33:45
 */
@Service
public class UserService extends BaseService<IUserDAO, UserDO> implements IUserService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode("123456"))
                .authorities("ROLE")
                .build();
    }
}
