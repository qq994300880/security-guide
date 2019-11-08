package com.yd.security.guide.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author YoungDream
 * @Date 2019/8/23 4:50
 */
@Data
@Entity
@Table(name = "user")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;
}
