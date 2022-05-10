package com.cloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/10 10:14 AM
 * @description
 */
@SpringCloudApplication
public class NacosAppication {
    public static void main(String[] args) {
        SpringApplication.run(NacosAppication.class, args);
    }
}
