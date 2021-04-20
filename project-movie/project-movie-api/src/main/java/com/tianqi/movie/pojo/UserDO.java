package com.tianqi.movie.pojo;

import java.io.Serializable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

import com.tianqi.common.pojo.BaseDO;

/**
 * User表：
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:06
 */
@Table(name = "user")
@Data
public class UserDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -75691601687488824L;

    @Column(name = "username")
    private String username;

}
