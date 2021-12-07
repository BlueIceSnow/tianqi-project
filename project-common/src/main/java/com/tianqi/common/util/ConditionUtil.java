package com.tianqi.common.util;

import com.tianqi.common.enums.database.BooleanEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @Author: yuantianqi
 * @Date: 2021/9/1 15:56
 * @Description:
 */
public class ConditionUtil {
    public static final String APP_ID = "app_id";
    public static final String TENANT_ID = "tenant_id";
    public static final String ORG_CODE = "org_code";
    public static final String IS_DELETE = "is_delete";
    public static final String ORDER_COLUMN = "sequence";
    private static final ThreadLocal<Map<String, Object>> CONDITION =
            ThreadLocal.withInitial(HashMap::new);

    public static void setAppId(final Integer appId) {
        CONDITION.get().put(APP_ID, appId);
    }

    public static void setTenantId(final Integer tenantId) {
        CONDITION.get().put(TENANT_ID, tenantId);
    }

    public static void setOrgCode(final String orgCode) {
        CONDITION.get().put(ORG_CODE, orgCode);
    }

    public static void setIsDelete(final BooleanEnum isDeleted) {
        CONDITION.get().put(IS_DELETE, isDeleted.getValue());
    }

    public static Map<String, Integer> getAppIdCond() {
        return getIdCond(APP_ID);
    }

    public static Map<String, Integer> getTenantIdCond() {
        return getIdCond(TENANT_ID);
    }

    public static Map<String, Integer> getIsDeletedCond() {
        final Map<String, Integer> idCond = getIdCond(IS_DELETE);
        if (idCond.size() == 0) {
            idCond.put(IS_DELETE, BooleanEnum.FALSE.getValue());
        }
        return idCond;
    }

    public static Map<String, String> getOrgCodeCond() {
        return getCodeCond();
    }

    public static void remove() {
        CONDITION.remove();
    }


    private static Map<String, Integer> getIdCond(final String key) {
        final HashMap<String, Integer> result = new HashMap<>(1);
        final Map<String, Object> cond = CONDITION.get();
        final Set<Map.Entry<String, Object>> entries = cond.entrySet();
        for (final Map.Entry<String, Object> entry : entries) {
            if (entry.getKey().equals(key)) {
                result.put(entry.getKey(),
                        Integer.valueOf(entry.getValue().toString()));
            }
        }
        return result;
    }

    private static Map<String, String> getCodeCond() {
        final HashMap<String, String> result = new HashMap<>(1);
        final Map<String, Object> cond = CONDITION.get();
        final Set<Map.Entry<String, Object>> entries = cond.entrySet();
        for (final Map.Entry<String, Object> entry : entries) {
            if (entry.getKey().equals(ConditionUtil.ORG_CODE)) {
                result.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return result;
    }

    public static <T> T global(final Supplier<T> supplier,
                               final Map<String, Object> params) {
        final Map<String, Object> backupMap = CONDITION.get();
        CONDITION.set(params);
        final T result = supplier.get();
        CONDITION.remove();
        CONDITION.set(backupMap);
        return result;
    }

}
