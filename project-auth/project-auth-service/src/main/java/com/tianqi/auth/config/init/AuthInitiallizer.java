package com.tianqi.auth.config.init;

import com.tianqi.auth.config.security.IJwtSecurityMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化工作
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/10/20 15:39:07
 */
@Component
public class AuthInitiallizer implements CommandLineRunner {
    IJwtSecurityMetaService metaService;

    @Autowired
    public void setMetaService(final IJwtSecurityMetaService metaService) {
        this.metaService = metaService;
    }

    @Override
    public void run(final String... args) {
        metaService.loadMetaAuthorities();
    }
}
