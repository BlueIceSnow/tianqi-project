package com.tianqi.client.config.sql;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.tianqi.common.util.ConditionUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.update.Update;

import java.util.List;

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
    protected void processPlainSelect(final PlainSelect plainSelect) {
        if (!(handler instanceof IsDeleteHandler)) {
            super.processPlainSelect(plainSelect);
            return;
        }
        final FromItem fromItem = plainSelect.getFromItem();
        final Expression where = plainSelect.getWhere();
        String whereSql = "";
        if (where != null) {
            whereSql = where.toString();
        }
        processWhereSubSelect(where);
        if (fromItem instanceof Table) {
            final Table fromTable = (Table) fromItem;
            if (!handler.ignoreTable(fromTable.getName()) &&
                    !whereSql.contains(ConditionUtil.IS_DELETE)) {
                //#1186 github
                plainSelect.setWhere(builderExpression(where, fromTable));
            }
        } else {
            processFromItem(fromItem);
        }
        //#3087 github
        final List<SelectItem> selectItems = plainSelect.getSelectItems();
        if (CollectionUtils.isNotEmpty(selectItems)) {
            selectItems.forEach(this::processSelectItem);
        }
        final List<Join> joins = plainSelect.getJoins();
        if (CollectionUtils.isNotEmpty(joins)) {
            joins.forEach(j -> {
                processJoin(j);
                processFromItem(j.getRightItem());
            });
        }
    }

    @Override
    protected void processUpdate(final Update update, final int index, final String sql,
                                 final Object obj) {
        if (!(handler instanceof IsDeleteHandler)) {
            super.processUpdate(update, index, sql, obj);
            return;
        }
        final Table table = update.getTable();
        if (handler.ignoreTable(table.getName())) {
            // 过滤退出执行
            return;
        }
        String whereSql = "";
        if (update.getWhere() != null) {
            whereSql = update.getWhere().toString();
        }
        if ((update.getWhere() == null ||
                !whereSql.contains(ConditionUtil.IS_DELETE))) {
            update.setWhere(this.andExpression(table, update.getWhere()));
        }
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
