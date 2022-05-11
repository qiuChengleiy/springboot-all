package com.cloud.rest.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/11 9:35 AM
 * @description
 */
@SpringCloudApplication
public class ProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}
