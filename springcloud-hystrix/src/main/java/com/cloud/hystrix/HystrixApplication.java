package com.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/12 11:06 AM
 * @description
 */
@SpringCloudApplication
public class HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    };
}
