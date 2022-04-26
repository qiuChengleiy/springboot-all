package com.example.app.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/13 10:43 AM
 * @description
 */
@Configuration
@MapperScan({"com.example.app.mapper"})
public class MyBatisConfig {}
