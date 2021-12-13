package com.tianqi.common.config.sentinel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * nacos配置属性
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/12/10 10:19:53
 */
@ConfigurationProperties(prefix = "tq.sentinel.nacos")
public class SentinelNacosProperties {
    private String address;
    private String username;
    private String password;
    private String namespace;
    private String groupId;
    @Value("${spring.application.name}")
    private String projectName;

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }
}
