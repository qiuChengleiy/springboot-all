package com.example.shiro.controller;

import com.example.shiro.dao.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/26 11:22 AM
 * @description
 */
@RestController
public class IndexController {

    @RequiresPermissions("p:user")
    @RequestMapping("/index")
    public String index(Model model) {
        // 登录成后，即可通过Subject获取登录的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index --->" + user.getUsername();
    }

}
