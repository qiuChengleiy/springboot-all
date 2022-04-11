package com.example.app.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/11 2:40 PM
 * @description
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String error(Exception e) {
        return e.getMessage();
    }
}
