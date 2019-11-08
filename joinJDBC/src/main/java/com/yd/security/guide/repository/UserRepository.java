package com.yd.security.guide.repository;

import com.yd.security.guide.entity.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YoungDream
 * @Date 2019/8/23 4:58
 */
@Repository
public interface UserRepository extends CrudRepository<UserDO, Long> {
    UserDO findByUsername(String username);
}
