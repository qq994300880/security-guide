package com.yd.security.guide.service.impl;

import com.yd.security.guide.entity.UserDO;
import com.yd.security.guide.repository.UserRepository;
import com.yd.security.guide.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author YoungDream
 * @Date 2019/8/23 5:03
 */
@Service
@Primary
@Slf4j
public class BaseUserService implements UserService {
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final UserRepository userRepository;

    public BaseUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean insert(UserDO userDO) {
        String username = userDO.getUsername();
        if (null != getByUsername(username)) {
            return false;
        } else {
            // 加密
            String newPassword = passwordEncoder.encode(userDO.getPassword());
            userDO.setPassword(newPassword);
            //保存
            userRepository.save(userDO);
            return true;
        }
    }

    @Override
    public UserDO getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
