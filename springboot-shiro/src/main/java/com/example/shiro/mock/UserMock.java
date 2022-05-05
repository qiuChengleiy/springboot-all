package com.example.shiro.mock;

import com.example.shiro.dao.User;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/26 11:14 AM
 * @description 用户数据模拟
 */
public class UserMock {

    public static String getPassword(String username) {
        System.out.println("用户名:   " + username);
        if(username.equals("admin")) {
            return "123";
        }else {
            return "456";
        }
    }

    public static String getRole(String username) {
        if(username.equals("admin")) {
            return "admin";
        }else {
            return "user";
        }
    }

    public static String getRoleById(String userId) {
        if(userId.equals("1")) {
            return "admin";
        }else {
            return "user";
        }
    }

    public static String getPermission(String username) {
        if(username.equals("admin")) {
            return "p:admin";
        }else {
            return "P:user";
        }
    }

    public static User getUserByUsername(String username) {
        User user = new User();
        if(username.equals("admin")) {
            user.setUsername("admin");
            user.setPassword("123");
            user.setRole("admin");
        }else {
            user.setUsername("admin1");
            user.setPassword("456");
            user.setRole("user");
        }
        user.setState("1");
        return user;
    }

}

