package com.example.app.controller;

import com.example.app.config.AppConfig;
import com.example.app.dto.Post;
import com.example.app.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/8 9:48 AM
 * @description
 */
@RestController
@RequestMapping("/v1")
public class HelloController {

    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    @Qualifier("Hello1ServiceImpl")
    private HelloService HelloService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!" + name + version;
    }

    @RequestMapping("/hello1")
    public String hello1(){
        return "Hello World!" + appConfig.getName() + appConfig.getVersion();
    }

    @RequestMapping("/hello2")
    public String hello2(){
        HelloService.hello();
        return "Hello World!";
    }

    @RequestMapping(value = "/hello3", headers = {}, method = RequestMethod.GET)
    public String hello3(){
        HelloService.hello();
        return "Hello World!";
    }

    @GetMapping("/hello4/{id}")
    public String hello4(@PathVariable String id){
        return "Hello World!" + id;
    }

    @GetMapping("/hello5/user")
    public String hello5(@RequestParam String name){
        return "Hello World!" + name;
    }

    @PostMapping("/hello6/create")
    public String hello6(@RequestBody String name){
        return "Hello World!" + name;
    }

    @PostMapping("/post/create")
    public String create(@RequestBody Post req){
        return "Hello World!" + req.getTitle();
    }

    @PostMapping("/post/update")
    public String update(@RequestBody Post req){
        return "Hello World!" + req.getContent();
    }

    @GetMapping("/hello7")
    public String hello7(HttpServletRequest req){
        return "Hello World!" + req.getHeader("Cookie");
    }
}
