package com.tianqi.auth.pojo.dto.resp;

import com.alibaba.fastjson.JSONObject;
import com.tianqi.common.enums.BooleanEnum;
import lombok.Data;

/**
 * 资源详情DTO
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/10/19 19:48:27
 */
@Data
public class ResourceDetailDTO {
    private Long id;
    private String name;
    private String url;
    private BooleanEnum closeable;
    private String component;
    private String icon;
    private String type;
    private Long parentId;
    private JSONObject extField;
}
