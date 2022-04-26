package com.example.app.controller;

import com.example.app.common.CommonPage;
import com.example.app.mapper.UserRoleMapper;
import com.example.app.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/13 11:06 AM
 * @description
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @GetMapping("role/list")
    @ResponseBody
    public CommonPage<UserRole> getUserRole() {
        CommonPage.setPageHelper(1, 10);
        List<UserRole> list = userRoleMapper.getRoles();
        return  CommonPage.restPage(list);
    }

    @GetMapping("role")
    @ResponseBody
    public UserRole getUserRole(@RequestParam Integer id) {
        return userRoleMapper.getRole(id);
    }

    @GetMapping("role/update")
    @ResponseBody
    public String updateUserRole(@RequestParam Integer id) {
        if(userRoleMapper.updateRole(id) <= 0) {
            return "fail";
        }

        return "success";
    }

    @GetMapping("role/add")
    @ResponseBody
    public String addUserRole(@RequestParam String name) {
        if(userRoleMapper.addRole(name) <= 0) {
            return "fail";
        }

        return "success";
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("trans")
    @ResponseBody
    public String transUser(@RequestParam String name) throws Exception {
        userRoleMapper.addRole(name);
        if(name.equals("xiaohong")) {
            throw new Exception("trans error");
        }

        userRoleMapper.updateRole(2);
        return "success";
    }
}
