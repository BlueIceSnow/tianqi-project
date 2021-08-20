package com.tianqi.auth.service;

import com.tianqi.auth.config.security.IJwtSecurityMetaService;
import com.tianqi.auth.pojo.TqUserDO;
import com.tianqi.common.service.IBaseService;

/**
 * (User)表服务接口
 *
 * @author yuantianqi
 * @since 2021-04-11 17:33:42
 */
public interface ITqUserService extends IBaseService<TqUserDO>, IJwtSecurityMetaService {

}
