package com.tianqi.auth.service;

import com.tianqi.auth.config.security.IJwtSecurityMetaService;
import com.tianqi.auth.pojo.TqAuthUserDO;
import com.tianqi.common.service.IBaseService;

/**
 * 用户表(TqAuthUser)表服务接口
 *
 * @author yuantianqi
 * @since 2021-08-25 19:27:44
 */
public interface ITqAuthUserService extends IBaseService<TqAuthUserDO>,
        IJwtSecurityMetaService {

}
