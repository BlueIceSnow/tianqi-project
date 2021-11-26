package com.tianqi.auth.pojo.dto.req;

import com.tianqi.auth.pojo.TqAuthTenantDO;
import com.tianqi.auth.pojo.TqAuthUserDO;
import lombok.Data;

/**
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/11/25 14:24:15
 */
@Data
public class TenantInfoDTO extends TqAuthTenantDO {
    private TqAuthUserDO mgr;
}
