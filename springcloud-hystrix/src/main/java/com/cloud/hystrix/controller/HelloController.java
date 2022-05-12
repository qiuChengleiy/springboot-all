package com.cloud.hystrix.controller;

import com.cloud.hystrix.model.User;
import com.cloud.hystrix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    /**
     * 服务容错测试
     * @return
     */
    @RequestMapping("/hello/user/hyx")
    @ResponseBody
    public User hello2() {
        return userService.hello("HystrixCommand");
    }
}
