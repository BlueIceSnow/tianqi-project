package com.tianqi.auth.convert;

import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Author: yuantianqi
 * @Date: 2021/11/2 14:31
 * @Description:
 */
@Mapper(componentModel = "spring")
public interface ResourceConvert {

    /**
     * DO生成ResourceDetailDTO
     *
     * @param resourceDO
     * @return
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "closeable", target = "closeable"),
            @Mapping(source = "component", target = "component"),
            @Mapping(source = "type", target = "type"),
            @Mapping(source = "parentId", target = "parentId"),
            @Mapping(source = "extField", target = "extField"),
    })
    ResourceDetailDTO doToResourceDetailDTO(TqAuthResourceDO resourceDO);
}
