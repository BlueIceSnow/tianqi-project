package com.tianqi.common.controller.impl;

import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * 基础控制层
 *
 * @author yuantianqi
 */
public abstract class BaseController<Service extends IBaseService, DO extends BaseDO>
        implements IBaseController<DO> {
    protected Service service;

    @Autowired
    public void setBaseBiz(Service service) {
        this.service = service;
    }

    @Override
    public ResultEntity<DO> getEntity(String id) {
        return service.getEntity(id);
    }

    @Override
    public ResultEntity<List<DO>> listEntity(@Validated DO entity) {
        return service.listEntity(entity);
    }

    @Override
    public ResultEntity<DO> listPageEntity(DO entity, int page, int size) {
        int i = 11/0;
        return service.listPageEntity(entity, page, size);
    }

    @Override
    public ResultEntity<DO> save(@Validated DO entity) {
        entity.setId(Timestamp.from(Instant.now()).toString());
        return service.save(entity);
    }

    @Override
    public ResultEntity<DO> update(DO entity) {
        return service.update(entity);
    }

    @GetMapping("page/{id}")
    @Override
    public ResultEntity<DO> removeByPage(DO entity, int page, int size, String id) {
        return service.removeByPage(entity, page, size, id);
    }

    @Override
    public ResultEntity<List<DO>> remove(DO entity, String id) {
        return service.remove(entity, id);
    }
}
