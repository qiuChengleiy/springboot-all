package com.example.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/8 9:48 AM
 * @description
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}
