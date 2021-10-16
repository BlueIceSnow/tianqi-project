package com.tianqi.common.controller.impl;

import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 基础控制层
 *
 * @Author yuantianqi
 */
public abstract class BaseControllerImpl<Service extends IBaseService<DO>, DO extends BaseDO>
        implements IBaseController<DO> {
    protected Service service;

    @Autowired
    public void setBaseBiz(final Service service) {
        this.service = service;
    }

    @Override
    public ResultEntity<DO> getEntity(final String id) {
        return service.getEntity(id);
    }

    @Override
    public ResultEntity<List<DO>> listEntity(@Validated final DO entity) {
        return service.listEntity(entity);
    }

    @Override
    public ResultEntity<List<DO>> listPageEntity(final DO entity, final int page,
                                                 final int size) {
        return service.listPageEntity(entity, page, size);
    }

    @Override
    public ResultEntity<DO> save(@Validated final DO entity) {
        return service.save(entity);
    }

    @Override
    public ResultEntity<DO> update(final DO entity) {
        return service.update(entity);
    }

    @GetMapping("page/{id}")
    @Override
    public ResultEntity<List<DO>> removeByPage(final DO entity, final int page, final int size,
                                               final String id) {
        return service.removeByPage(entity, page, size, id);
    }

    @Override
    public ResultEntity<List<DO>> remove(final DO entity, final String id) {
        return service.remove(entity, id);
    }
}
