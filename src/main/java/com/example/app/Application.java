package com.example.app;

import com.example.app.config.AppConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/8 9:45 AM
 * @description
 */
@EnableConfigurationProperties({AppConfig.class})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        // 关闭 banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
