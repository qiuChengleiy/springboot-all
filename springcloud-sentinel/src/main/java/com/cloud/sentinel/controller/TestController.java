package com.cloud.sentinel.controller;

import com.cloud.sentinel.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qcl
 * @version 1.0
 * @date 2021/12/9 2:45 下午
 * @description
 */
@RestController
public class TestController {

    @Autowired
    private HelloService hello;

    /**
     * 浏览器访问 用工具访问不起作用
     * sentinel控制台就会不戳到应用
     * @return
     */
    @GetMapping("/test")
    public String test() throws InterruptedException  {
      //  TimeUnit.SECONDS.sleep(5);
        return "test" + hello.hello();
    }

    @GetMapping("/hello")
    public String test1() throws InterruptedException  {
//        TimeUnit.SECONDS.sleep(1);
        return "hello" + hello.hello();
    }
}
