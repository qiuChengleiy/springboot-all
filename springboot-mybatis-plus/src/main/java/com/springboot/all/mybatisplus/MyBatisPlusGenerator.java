package com.springboot.all.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Author qcl
 * @Description
 * @Date 4:39 PM 12/14/2023
 */
public class MyBatisPlusGenerator {

    public Map<String, Object> getPackageInfo() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("packageName", "com.springboot.all.mybatisplus");
        map.put("upperClassName", "User");
        map.put("lowerClassName", "user");
        return map;
    }

    @Test
    public void generate() {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false", "root", "qcl123456")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("pkq") // 设置作者
                            .commentDate("yyyy-MM-dd hh:mm:ss")   //注释日期
                            .outputDir("./src/main/java") // 指定输出目录
                            .disableOpenDir() //禁止打开输出目录，默认打开
                    ;
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.springboot.all.mybatisplus") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "./src/main/resources/mappers")); // 设置mapperXml生成路径
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.ENTITY).controller("/templates/controller.java").build();
                })
                .injectionConfig(builder -> {
                    builder.customMap(getPackageInfo()).build();
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("sys_") // 设置过滤表前缀
                            // Entity 策略配置
                            .entityBuilder()
                            .enableLombok() //开启 Lombok
                            .enableFileOverride() // 覆盖已生成文件
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                            // Mapper 策略配置
                            .mapperBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            // Service 策略配置
                            .serviceBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                            // Controller 策略配置
                            .controllerBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                    ;
                })
                .execute();
    }
}
