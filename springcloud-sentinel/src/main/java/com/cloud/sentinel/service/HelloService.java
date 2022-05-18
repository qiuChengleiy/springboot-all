package com.cloud.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author qcl
 * @version 1.0
 * @date 2021/12/9 4:29 下午
 * @description
 */
@Service
public class HelloService {
    @SentinelResource(value= "hello", fallback = "helloCallback", blockHandler = "helloCallback1")
    public String hello() {
//        try {
//            throw new Exception("报错了");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "hello";
    }

    // 异常回退 -- 触发异常 /  (触发规则 如果没有helloCallback1则会回调到该方法)
    public String helloCallback(Throwable throwable) {
        return "这是异常回调 ---> " + throwable.getMessage();
    }

    // sentinel回退 / 触发规则
    public String helloCallback1(BlockException e) {
        return "sentinel 回退 ---> " + e.getMessage();
    }
}
