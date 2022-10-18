package com.example.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/8 10:34 AM
 * @description
 */
@ConfigurationProperties(prefix= "app")
public class AppConfig {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String name;

    private String version;
}
