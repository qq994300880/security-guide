package com.yd.security.guide.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author YoungDream
 * @Date 2019/8/23 11:48
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        //添加自定义拦截器
        http.addFilterAt(customFromLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //自定义认证过滤器
    private CustomFromLoginFilter customFromLoginFilter() {
        return new CustomFromLoginFilter("/login");
    }
}
