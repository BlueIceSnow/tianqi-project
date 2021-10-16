package com.tianqi.client.controller;

import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * controller层接口
 *
 * @Author yuantianqi
 */
public interface IBaseController<DO extends BaseDO> {

    /**
     * 根据ID查询实体信息
     *
     * @param id 实体主键
     * @return 实体响应对象
     */
    @GetMapping("{id}")
    ResultEntity<DO> getEntity(@PathVariable("id") String id);

    /**
     * 查询所有实体
     *
     * @param entity 请求参数
     * @return 返回实体类表
     */
    @GetMapping
    ResultEntity<List<DO>> listEntity(DO entity);


    /**
     * 查询单个实体
     *
     * @param entity 请求参数
     * @param page   当前页，默认值1
     * @param size   每页大小，默认值10
     * @return 返回分页实体列表
     */
    @GetMapping("page")
    ResultEntity<List<DO>> listPageEntity(DO entity
            , @RequestParam(value = "page", defaultValue = "1") int page
            , @RequestParam(value = "size", defaultValue = "10") int size);

    /**
     * 保存实体
     *
     * @param entity 保存的实体数据
     * @return 保存后的实体数据
     */
    @PostMapping
    ResultEntity<DO> save(@RequestBody DO entity);

    /**
     * 更新实体
     *
     * @param entity 更新的实体数据
     * @return 更新后的实体数据
     */
    @PutMapping
    ResultEntity<DO> update(@RequestBody DO entity);

    /**
     * 分页删除实体
     *
     * @param entity 条件查询参数
     * @param page   当前页
     * @param size   每页大小
     * @param id     实体ID
     * @return 返回删除数据后当前页实体列表
     */
    @Delete("page/{id}")
    ResultEntity<List<DO>> removeByPage(DO entity
            , @RequestParam("page") int page, @RequestParam("size") int size,
                                        @PathVariable("id") String id);

    /**
     * 删除实体
     *
     * @param id     实体ID
     * @param entity 查询参数
     * @return 返回删除实体后数据列表
     */
    @Delete("{id}")
    ResultEntity<List<DO>> remove(DO entity, @PathVariable("id") String id);
}
