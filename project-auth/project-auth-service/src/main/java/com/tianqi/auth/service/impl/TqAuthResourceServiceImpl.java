package com.tianqi.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tianqi.auth.dao.ITqAuthResourceDAO;
import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.service.ITqAuthResourceService;
import com.tianqi.common.enums.ResourceTypeEnum;
import com.tianqi.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源表(TqAuthResource)表服务实现类
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:45
 */
@Service
public class TqAuthResourceServiceImpl
        extends BaseServiceImpl<ITqAuthResourceDAO, TqAuthResourceDO>
        implements ITqAuthResourceService {

    /**
     * 根据应用ID及类型查询资源类表
     *
     * @param appId 应用ID
     * @param type  类型
     * @return
     */
    @Override
    public List<TqAuthResourceDO> selectResourceListByAppId(final Integer appId,
                                                            final ResourceTypeEnum type) {
        final QueryWrapper<TqAuthResourceDO> resourceQuery =
                new QueryWrapper<>();
        resourceQuery.lambda().eq(TqAuthResourceDO::getAppId, appId);
        resourceQuery.lambda().eq(TqAuthResourceDO::getType, type);
        return dao.selectList(resourceQuery);
    }
}
