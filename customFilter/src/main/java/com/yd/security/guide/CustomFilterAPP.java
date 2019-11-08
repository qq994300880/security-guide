package com.yd.security.guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author YoungDream
 * @Date 2019/8/23 11:38
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CustomFilterAPP {
    public static void main(String[] args) {
        SpringApplication.run(CustomFilterAPP.class, args);
    }
}