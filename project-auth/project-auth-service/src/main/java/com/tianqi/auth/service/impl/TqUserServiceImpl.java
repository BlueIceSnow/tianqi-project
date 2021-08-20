package com.tianqi.auth.service.impl;

import com.tianqi.auth.config.security.authorization.JwtAuthority;
import com.tianqi.auth.config.security.authorization.JwtConfigAttribute;
import com.tianqi.auth.dao.ITqUserDAO;
import com.tianqi.auth.pojo.TqUserDO;
import com.tianqi.auth.service.ITqUserService;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务实现类
 *
 * @author yuantianqi
 * @since 2021-04-11 17:33:45
 */
@Service
public class TqUserServiceImpl extends BaseServiceImpl<ITqUserDAO, TqUserDO>
        implements ITqUserService {
    PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(
            final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TqUserDO loadUserInfo(String username) {
        if (username.equals("blue")) {
            final TqUserDO userDO = new TqUserDO();
            userDO.setUsername("blue");
            userDO.setPassword(passwordEncoder.encode("123456"));
            return userDO;
        }
        return null;
    }

    @Override
    public List<String> loadIgnoringAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public List<JwtAuthority> loadAuthorities(final String username) {
        final JwtAuthority role_admin = new JwtAuthority("TQ:ROLE:ADMIN");
        final JwtAuthority role_test_blue = new JwtAuthority("TQ:METHOD:BLUE");

        return Arrays.asList(role_admin, role_test_blue);
    }

    @Override
    public Map<String, List<ConfigAttribute>> loadMetaAuthorities() {

        final LinkedHashMap<String, List<ConfigAttribute>> urlAuthMapping =
                new LinkedHashMap<>();
//        urlAuthMapping.put("/user/page"
//                , Arrays.asList(new JwtConfigAttribute("TQ:ROLE:ADMIN")));
        urlAuthMapping.put("/user/test"
                , Arrays.asList(new JwtConfigAttribute("TQ:METHOD:BLUE")));
        return urlAuthMapping;
    }
}
