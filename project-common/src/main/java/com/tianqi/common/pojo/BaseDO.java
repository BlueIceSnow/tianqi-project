package com.tianqi.common.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 基础持久层
 *
 * @author yuantianqi
 */
@Data
public class BaseDO implements Serializable {
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 名称
     */
    @Column(name = "name")
    @NotNull(message = "不允许为空")
    private String name;
    /**
     * 是否删除，逻辑删除
     */
    @Column(name = "is_delete")
    private Boolean deleted;
    /**
     * 创建用户
     */
    @Column(name = "create_user")
    private String createUser;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Timestamp createTime;
    /**
     * 更新用户
     */
    @Column(name = "update_user")
    private String updateUser;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Timestamp updateTime;
    /**
     * 所属租户
     */
    @Column(name = "tent_id")
    private String tentId;
}
