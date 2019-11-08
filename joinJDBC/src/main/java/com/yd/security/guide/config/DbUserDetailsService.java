package com.yd.security.guide.config;

import com.yd.security.guide.entity.UserDO;
import com.yd.security.guide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author YoungDream
 * @Date 2019/8/23 5:08
 */
@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public DbUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO u = userService.getByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("USER"));
        return new User(u.getUsername(), u.getPassword(), list);
    }
}