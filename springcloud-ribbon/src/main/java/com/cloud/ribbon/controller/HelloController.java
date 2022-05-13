package com.cloud.ribbon.controller;


import com.cloud.ribbon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/11 9:44 AM
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello/user/{id:\\d+}")
    @ResponseBody
    public User hello1(@PathVariable String id) {

        Map<String, String> us = new HashMap<>();
        us.put("username", "java rest api -- ");
        us.put("username", us.get("username") + id);
        User u = restTemplate.postForObject("http://provider/hello/app/post", us, User.class);

        return u;
    }
}
