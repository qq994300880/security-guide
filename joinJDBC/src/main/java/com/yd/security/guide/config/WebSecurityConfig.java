package com.yd.security.guide.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author YoungDream
 * @Date 2019/8/23 10:08
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private DbUserDetailsService dbUserDetailsService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setDbUserDetailsService(DbUserDetailsService dbUserDetailsService) {
        this.dbUserDetailsService = dbUserDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/favicon", "/static/**").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(dbUserDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * Spring Security在版本5中引入了一些重大更改。其中之一是在散列中包含用于散列密码的算法。 这允许更容易的迁移。
     * 密码的一般格式是： {id}encodedPassword
     *
     * 作为一个方面说明：如果您将密码存储在数据库中并且设置了确切的修复长度，这也可能导致出现这种情况
     * 即您可以非常方便地截断哈希的结尾，因为前面的ID是哈希的长度增加。
     * 将一个项目从Spring Boot 1 / Spring 4迁移到Spring Boot 2 / Spring 5，然后从BCrypt迁移到PBKDF2。
     * 密码编码器现在看起来像这样：
     * public PasswordEncoder passwordEncoder() {
     *     // This is the ID we use for encoding.
     *     String currentId = "pbkdf2.2018";
     *
     *     // List of all encoders we support. Old ones still need to be here for rolling updates
     *     Map<String, PasswordEncoder> encoders = new HashMap<>();
     *     encoders.put("bcrypt", new BCryptPasswordEncoder());
     *     encoders.put(currentId, new Pbkdf2PasswordEncoder(PBKDF2_2018_SECRET, PBKDF2_2018_ITERATIONS, PBKDF2_2018_HASH_WIDTH));
     *
     *     return new DelegatingPasswordEncoder(currentId, encoders);
     * }
     * 它还需要更新数据库并用{bcrypt}前缀所有当前散列（我之前专门使用了BCrypt）
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        // This is the ID we use for encoding.
        String oneId = "yd.bcrypt.2019";
        String twoId = "yd.pbkdf2.2019";
        // List of all encoders we support. Old ones still need to be here for rolling updates
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(oneId, new BCryptPasswordEncoder());
        encoders.put(twoId, new Pbkdf2PasswordEncoder("youngdream", 185000, 256));
        return new DelegatingPasswordEncoder(twoId, encoders);
    }
}
