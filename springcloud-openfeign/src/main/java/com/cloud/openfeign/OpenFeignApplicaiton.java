package com.cloud.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/16 9:35 AM
 * @description
 */
@EnableFeignClients(basePackages = "com.cloud.openfeign.api")
@SpringCloudApplication
public class OpenFeignApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignApplicaiton.class, args);
    }
}
