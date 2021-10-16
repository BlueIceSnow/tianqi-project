package com.tianqi.auth.service;

import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.common.enums.ResourceTypeEnum;
import com.tianqi.common.service.IBaseService;

import java.util.List;

/**
 * 资源表(TqAuthResource)表服务接口
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:45
 */
public interface ITqAuthResourceService extends IBaseService<TqAuthResourceDO> {

    /**
     * 根据AppId及类型检索所有的资源
     *
     * @param appId 应用ID
     * @param type  类型
     * @return
     */
    List<TqAuthResourceDO> selectResourceListByAppId(Integer appId,
                                                     ResourceTypeEnum type);
}
