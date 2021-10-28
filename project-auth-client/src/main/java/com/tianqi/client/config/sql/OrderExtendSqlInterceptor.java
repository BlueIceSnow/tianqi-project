package com.tianqi.client.config.sql;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.tianqi.common.util.ConditionUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/10/28 15:38:01
 */
public class OrderExtendSqlInterceptor extends JsqlParserSupport implements InnerInterceptor {

    @Override
    public void beforeQuery(final Executor executor, final MappedStatement ms,
                            final Object parameter,
                            final RowBounds rowBounds, final ResultHandler resultHandler,
                            final BoundSql boundSql)
            throws SQLException {
        final PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        mpBs.sql(parserSingle(mpBs.sql(), null));
    }

    @Override
    protected void processSelect(final Select select, final int index, final String sql,
                                 final Object obj) {
        processSelectBody(select.getSelectBody());
        final List<WithItem> withItemsList = select.getWithItemsList();
        if (!CollectionUtils.isEmpty(withItemsList)) {
            withItemsList.forEach(this::processSelectBody);
        }
    }

    protected void processSelectBody(final SelectBody selectBody) {
        if (selectBody == null) {
            return;
        }
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
        } else if (selectBody instanceof WithItem) {
            final WithItem withItem = (WithItem) selectBody;
            processSelectBody(withItem.getSelectBody());
        } else {
            final SetOperationList operationList = (SetOperationList) selectBody;
            final List<SelectBody> selectBodys = operationList.getSelects();
            if (CollectionUtils.isNotEmpty(selectBodys)) {
                selectBodys.forEach(this::processSelectBody);
            }
        }
    }

    /**
     * 处理 PlainSelect
     */
    protected void processPlainSelect(final PlainSelect plainSelect) {
        final FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            final Table fromTable = (Table) fromItem;
            final StringBuilder alias = new StringBuilder();
            final OrderByElement orderByElement = new OrderByElement();
            if (fromTable.getAlias() != null) {
                alias.append(fromTable.getAlias().getName()).append(StringPool.DOT);
            }
            orderByElement
                    .setExpression(new Column(alias.append(ConditionUtil.ORDER_COLUMN).toString()));
            orderByElement.setAsc(true);
            plainSelect.addOrderByElements(orderByElement);
        }
    }
}
