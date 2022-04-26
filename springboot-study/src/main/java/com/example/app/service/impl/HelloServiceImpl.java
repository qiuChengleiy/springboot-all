package com.example.app.service.impl;

import com.example.app.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/8 11:05 AM
 * @description
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello() {
        System.out.println("hello service");
    }
}
