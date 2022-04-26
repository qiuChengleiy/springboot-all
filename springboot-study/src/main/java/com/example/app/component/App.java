package com.example.app.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/8 10:59 AM
 * @description
 */
@Component
public class App {

    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;

    public String getName() {
        return name;
    }


    public String getVersion() {
        return version;
    }
}
