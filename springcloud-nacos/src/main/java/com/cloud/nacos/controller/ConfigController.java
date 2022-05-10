package com.cloud.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/10 10:36 AM
 * @description
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${app:name}")
    private String appName;

    @GetMapping("/api/appInfo")
    public String appInfo() {
        return "appName: " + appName;
    }
}
