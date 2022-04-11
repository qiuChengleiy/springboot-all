package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/11 10:53 AM
 * @description
 */
@Controller
public class WebController {

    @RequestMapping
    public String index(HashMap<String,Object> map) {
        map.put("data","我是数据data");
        return "index";
    }
}
