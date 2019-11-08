package com.yd.security.guide.service;

import com.yd.security.guide.entity.UserDO;

/**
 * @Author YoungDream
 * @Date 2019/8/23 5:01
 */
public interface UserService {
    boolean insert(UserDO userDO);

    UserDO getByUsername(String username);
}
