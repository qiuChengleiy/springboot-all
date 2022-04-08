package com.example.app.service.impl;

import com.example.app.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/8 11:28 AM
 * @description
 */
@Service("Hello1ServiceImpl")
public class Hello1ServiceImpl implements HelloService {
    @Override
    public void hello() {
        System.out.println("Hello1ServiceImpl");
    }
}
