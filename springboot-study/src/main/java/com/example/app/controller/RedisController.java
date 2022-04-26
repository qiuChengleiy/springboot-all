package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/15 10:12 AM
 * @description
 */
@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public String setData() {
        String key = "data";
        stringRedisTemplate.opsForValue().set(key, "dataVal");
        return "success";
    }

    @GetMapping("/expire")
    public String expireData() {
        String key = "data";
        if(!stringRedisTemplate.expire(key, 10, TimeUnit.SECONDS)) {
            return "fail";
        }
        return "success";
    }

    @GetMapping("/del")
    public String deleteData() {
        String key = "data";
        stringRedisTemplate.delete(key);
        return "success";
    }

    @GetMapping("/increment")
    public Long incrementData() {
        String key = "data";
        return stringRedisTemplate.opsForValue().increment(key,1);
    }


}
