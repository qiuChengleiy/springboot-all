package com.cloud.openfeign.controller;


import com.cloud.openfeign.api.OpenFeignService;
import com.cloud.openfeign.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    OpenFeignService openFeignService;

    @RequestMapping("/hello/user/{id:\\d+}")
    @ResponseBody
    public User hello(@PathVariable String id) {
        User user = new User();
        user.setUsername(id);
        return openFeignService.getUser(user);
    }
}
