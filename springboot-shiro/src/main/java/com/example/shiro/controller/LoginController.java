package com.example.shiro.controller;

import com.example.shiro.dao.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/26 10:53 AM
 * @description
 */
@RestController
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password) {
        //UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 获取Subject对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        Subject subject = SecurityUtils.getSubject();
        try {
            // 执行登录
            subject.login(token);
            return "ok";
        } catch (UnknownAccountException e) {
            return e.getMessage();
        } catch (IncorrectCredentialsException e) {
            return "IncorrectCredentialsException " + e.getMessage();
        } catch (LockedAccountException e) {
            return "LockedAccountException " + e.getMessage();
        } catch (AuthenticationException e) {
            return "认证失败！";
        }
    }
}
