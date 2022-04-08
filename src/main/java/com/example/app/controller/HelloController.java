package com.example.app.controller;

import com.example.app.config.AppConfig;
import com.example.app.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/8 9:48 AM
 * @description
 */
@RestController
@RequestMapping("/v1")
public class HelloController {

    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    @Qualifier("Hello1ServiceImpl")
    private HelloService HelloService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!" + name + version;
    }

    @RequestMapping("/hello1")
    public String hello1(){
        return "Hello World!" + appConfig.getName() + appConfig.getVersion();
    }

    @RequestMapping("/hello2")
    public String hello2(){
        HelloService.hello();
        return "Hello World!";
    }
}
