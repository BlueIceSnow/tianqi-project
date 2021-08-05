package com.tianqi.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianqi.common.pojo.BaseDO;

/**
 * 持久层封装
 *
 * @author yuantianqi
 */
public interface IBaseDAO<DO extends BaseDO> extends BaseMapper<DO> {
}
