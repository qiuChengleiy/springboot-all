package com.cloud.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/11 9:43 AM
 * @description
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // 这样可以使用服务对象调用  http://provicer/app/hello
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(6000);
        factory.setConnectTimeout(3000);
        return factory;
    }
}
