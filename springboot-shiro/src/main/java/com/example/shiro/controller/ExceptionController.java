package com.example.shiro.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/28 10:54 AM
 * @description
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = AuthorizationException.class)
    public String handleAuthorizationException() {
        return "您当前没有权限访问~ 请联系管理员";
    }
}
