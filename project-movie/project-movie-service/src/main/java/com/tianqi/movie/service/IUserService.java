package com.tianqi.movie.service;

import com.tianqi.client.config.security.IJwtSecurityMetaService;
import com.tianqi.common.service.IBaseService;
import com.tianqi.movie.pojo.UserDO;

/**
 * (User)表服务接口
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:11
 */
public interface IUserService extends IBaseService<UserDO>, IJwtSecurityMetaService {

}
