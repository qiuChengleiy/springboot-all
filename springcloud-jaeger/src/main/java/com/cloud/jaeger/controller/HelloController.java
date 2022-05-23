package com.cloud.jaeger.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/11 9:44 AM
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hello/user/{id:\\d+}")
    @ResponseBody
    public String hello(@PathVariable String id) {
        return id;
    }
}
