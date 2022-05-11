package com.cloud.rest.consumer.controller;

import com.cloud.rest.consumer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/11 9:44 AM
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/discoverSvrInfo")
    @ResponseBody
    public URI getSvrUrl() throws Exception {
        // 如果是多个服务提供者
        ServiceInstance serviceInstance;
        if(true) {
            List<ServiceInstance> services = discoveryClient.getInstances("provider");
            serviceInstance = services.size() > 0 ? services.get(0) : null;
        }else {
            serviceInstance = loadBalancerClient.choose("provider");
        }

        // 发起调用
        // 1. 不同命名空间下 调用，会导致找不到实例
        if(serviceInstance == null) {
            throw new Exception("未获取到实例");
        }

        return serviceInstance.getUri();
    }

    @RequestMapping("/hello/user/{id:\\d+}")
    @ResponseBody
    public User hello1(@PathVariable String id) {

        // restTemplate
        // GET
        // 1. getForEntity {name} 是站位 id是传值
        // 指定返回对象类型
        User u = restTemplate.getForEntity("http://provider/hello/app/{name}", User.class, id).getBody();
//        {
//            "username": "hello java456"
//        }
        // id参数 也可以放在hashmap里
        Map<String, Object> params = new HashMap<>();
        params.put("name", id);
        u = restTemplate.getForEntity("http://provider/hello/app/{name}", User.class, params).getBody();


        // 2. getForObject 和1类似， 可以站位，和接收map参数, 区别在于返回的类型是 getBody内容
        u = restTemplate.getForObject("http://provider/hello/app/{name}", User.class, params);

        // post请求 主要有三种 postForObject  postForEntity  postForLocation
        Map<String, String> us = new HashMap<>();
        us.put("username", "java rest api -- ");
        us.put("username", us.get("username") + id);
        u = restTemplate.postForObject("http://provider/hello/app/post", us, User.class);

        // 其它 与上述类似
        // put   r.put(...)   delete r.delete(....)
        return u;
    }
}
