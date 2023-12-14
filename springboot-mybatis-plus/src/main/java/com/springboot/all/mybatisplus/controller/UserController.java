package com.springboot.all.mybatisplus.controller;

import com.springboot.all.mybatisplus.entity.User;
import com.springboot.all.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @ResponseBody
    public List<User> getUserList() {
        return userService.getUserInfo();
    }
}
