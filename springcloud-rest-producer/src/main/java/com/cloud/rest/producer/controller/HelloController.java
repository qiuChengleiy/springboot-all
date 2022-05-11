package com.cloud.rest.producer.controller;

import com.cloud.rest.producer.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/11 9:44 AM
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/app/{id:\\d+}")
    @ResponseBody
    public String hello1(@PathVariable String id) {
        return "hello " + id;
    }

    @PostMapping("/app/post")
    @ResponseBody
    public User hello2(@RequestBody User u) {
        return u;
    }
}
