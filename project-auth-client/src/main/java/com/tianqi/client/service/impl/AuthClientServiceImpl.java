package com.tianqi.client.service.impl;

import com.tianqi.client.service.IAuthClientService;
import org.springframework.security.access.ConfigAttribute;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/22 10:58
 * @Description:
 */
public class AuthClientServiceImpl implements IAuthClientService {
    @Override
    public List<String> loadIgnoringAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, List<ConfigAttribute>> loadMetaAuthorities() {
        return new LinkedHashMap<>();
    }
}
