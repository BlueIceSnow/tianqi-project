package com.tianqi.auth.config.security.interceptor;

import com.tianqi.auth.util.SqlConditionUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.schema.Column;

import java.util.Map;
import java.util.Set;

/**
 * @Author: yuantianqi
 * @Date: 2021/9/2 09:24
 * @Description:
 */
public interface AuthHandler {

    /**
     * 获取租户 ID 值表达式，只支持单个 ID 值
     * <p>
     *
     * @return 租户 ID 值表达式
     */
    default Expression getTenantId() {
        final Map<String, Integer> tenantIdCond = SqlConditionUtil.getTenantIdCond();
        return getExpression(tenantIdCond);
    }

    /**
     * 获取租户字段名
     * <p>
     * 默认字段名叫: tenant_id
     *
     * @return 租户字段名
     */
    default String getTenantIdColumn() {
        return SqlConditionUtil.TENANT_ID;
    }

    /**
     * 获取租户 ID 值表达式，只支持单个 ID 值
     * <p>
     *
     * @return 租户 ID 值表达式
     */
    default Expression getAppId() {
        final Map<String, Integer> tenantIdCond = SqlConditionUtil.getAppIdCond();
        return getExpression(tenantIdCond);
    }

    /**
     * 获取表达式
     *
     * @param intIdCond 整型 ID 的条件表达式构建
     * @return
     */
    private Expression getExpression(final Map<String, Integer> intIdCond) {
        final Set<Map.Entry<String, Integer>> entries = intIdCond.entrySet();
        for (final Map.Entry<String, Integer> entry : entries) {
            return new EqualsTo(new Column(entry.getKey()),
                    new LongValue(entry.getValue()));
        }
        return null;
    }

    /**
     * 获取 AppId 字段名
     * <p>
     * 默认字段名叫: app_id
     *
     * @return AppId 字段名
     */
    default String getAppIdColumn() {
        return SqlConditionUtil.APP_Id;
    }

    /**
     * 获取 OrgCode 值表达式，只支持单个 OrgCode 值
     * <p>
     *
     * @return 租户 ID 值表达式
     */
    default Expression getOrgCode() {
        final Map<String, String> tenantIdCond = SqlConditionUtil.getOrgCodeCond();
        final Set<Map.Entry<String, String>> entries = tenantIdCond.entrySet();
        for (final Map.Entry<String, String> entry : entries) {
            final LikeExpression likeExpression = new LikeExpression();
            likeExpression.withLeftExpression(new Column(entry.getKey()));
            likeExpression.withRightExpression(new StringValue("%" + entry.getValue()));
            return likeExpression;
        }
        return null;
    }

    /**
     * 获取组织字段名
     * <p>
     * 默认字段名叫: org_code
     *
     * @return 租户字段名
     */
    default String getOrgCodeColumn() {
        return SqlConditionUtil.ORG_CODE;
    }

    /**
     * 根据表名判断是否忽略拼接
     * <p>
     * 默认都要进行解析并拼接
     *
     * @param tableName 表名
     * @return 是否忽略, true:表示忽略，false:需要解析并拼接
     */
    default boolean ignoreTable(final String tableName) {
        return SqlConditionUtil.getOrgCodeCond().size() == 0 &&
                SqlConditionUtil.getAppIdCond().size() == 0 &&
                SqlConditionUtil.getTenantIdCond().size() == 0;
    }
}
