package com.cloud.jaeger;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/23 10:16 AM
 * @description
 */
@SpringCloudApplication
public class JaegerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JaegerApplication.class, args);
    }
}
