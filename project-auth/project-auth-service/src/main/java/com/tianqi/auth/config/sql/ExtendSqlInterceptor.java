package com.tianqi.auth.config.sql;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.schema.Table;

/**
 * @Author: yuantianqi
 * @Date: 2021/9/2 10:15
 * @Description:
 */
public class ExtendSqlInterceptor extends TenantLineInnerInterceptor {
    private final TenantLineHandler handler;

    public ExtendSqlInterceptor(final TenantLineHandler handler) {
        super(handler);
        this.handler = handler;
    }

    @Override
    protected Expression builderExpression(final Expression currentExpression,
                                           final Table table) {
        final Expression columnValue = handler.getTenantId();
        Expression expression = null;
        if (handler instanceof AppIdHandler || handler instanceof IsDeleteHandler ||
                handler instanceof TenantIdHandler) {
            expression = new EqualsTo(this.getAliasColumn(table), columnValue);
        } else if (handler instanceof OrgCodeHandler) {
            expression = new LikeExpression();
            ((LikeExpression) expression).withLeftExpression(this.getAliasColumn(table));
            ((LikeExpression) expression).withRightExpression(columnValue);
        } else {
            return currentExpression;
        }
        if (currentExpression == null) {
            return expression;
        }
        if (currentExpression instanceof OrExpression) {
            return new AndExpression(new Parenthesis(currentExpression), expression);
        } else {
            return new AndExpression(currentExpression, expression);
        }
    }
}
