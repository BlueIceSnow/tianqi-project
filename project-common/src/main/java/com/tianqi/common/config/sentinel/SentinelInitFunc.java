package com.tianqi.common.config.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.tianqi.common.util.NacosConfigUtil;
import com.tianqi.common.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Properties;

/**
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/12/07 17:09:22
 */
@Slf4j
public class SentinelInitFunc implements InitFunc {
    @Override
    public void init() throws Exception {
        final SentinelNacosProperties nacosProperties =
                SpringUtil.getBean(SentinelNacosProperties.class);
        FlowRuleManager.register2Property(flowDataSource(nacosProperties).getProperty());
        ParamFlowRuleManager.register2Property(paramFlowDataSource(nacosProperties).getProperty());
        DegradeRuleManager.register2Property(degradeRuleDataSource(nacosProperties).getProperty());
        SystemRuleManager.register2Property(systemRuleDataSource(nacosProperties).getProperty());
        AuthorityRuleManager
                .register2Property(authorityRuleDataSource(nacosProperties).getProperty());
    }

    public ReadableDataSource<String, List<FlowRule>> flowDataSource(
            final SentinelNacosProperties nacosProperties) {
        final Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getAddress());
        properties.put(PropertyKeyConst.NAMESPACE, nacosProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
        return new NacosDataSource<>(properties, nacosProperties.getGroupId(),
                nacosProperties.getProjectName() + NacosConfigUtil.FLOW_DATA_ID_POSTFIX,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
    }

    public ReadableDataSource<String, List<ParamFlowRule>> paramFlowDataSource(
            final SentinelNacosProperties nacosProperties) {
        final Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getAddress());
        properties.put(PropertyKeyConst.NAMESPACE, nacosProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
        return new NacosDataSource<>(properties, nacosProperties.getGroupId(),
                nacosProperties.getProjectName() + NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX,
                source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
                }));
    }

    public ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource(
            final SentinelNacosProperties nacosProperties) {
        final Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getAddress());
        properties.put(PropertyKeyConst.NAMESPACE, nacosProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
        return new NacosDataSource<>(properties, nacosProperties.getGroupId(),
                nacosProperties.getProjectName() + NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX,
                source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
                }));
    }

    public ReadableDataSource<String, List<SystemRule>> systemRuleDataSource(
            final SentinelNacosProperties nacosProperties) {
        final Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getAddress());
        properties.put(PropertyKeyConst.NAMESPACE, nacosProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
        return new NacosDataSource<>(properties, nacosProperties.getGroupId(),
                nacosProperties.getProjectName() + NacosConfigUtil.SYSTEM_DATA_ID_POSTFIX,
                source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {
                }));
    }

    public ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource(
            final SentinelNacosProperties nacosProperties) {
        final Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getAddress());
        properties.put(PropertyKeyConst.NAMESPACE, nacosProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
        return new NacosDataSource<>(properties, nacosProperties.getGroupId(),
                nacosProperties.getProjectName() + NacosConfigUtil.AUTHORITY_DATA_ID_POSTFIX,
                source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {
                }));
    }
}
