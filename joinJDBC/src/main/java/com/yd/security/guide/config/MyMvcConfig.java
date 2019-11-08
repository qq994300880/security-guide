package com.yd.security.guide.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author YoungDream
 * @Date 2019/8/22 22:41
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {
//    @Override
//    protected void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/home").setViewName("index");
//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/logout").setViewName("logout");
//    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/favicon.ico");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //使用webjar
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
