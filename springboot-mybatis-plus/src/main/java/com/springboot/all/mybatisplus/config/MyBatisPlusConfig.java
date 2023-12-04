package com.springboot.all.mybatisplus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author qcl
 * @Description
 * @Date 11:07 AM 12/4/2023
 */
@Configuration
@MapperScan("com.springboot.all.mybatisplus.mapper")
public class MyBatisPlusConfig {
}
