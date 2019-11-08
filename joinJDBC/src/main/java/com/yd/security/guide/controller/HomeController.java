package com.yd.security.guide.controller;

import com.yd.security.guide.entity.UserDO;
import com.yd.security.guide.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author YoungDream
 * @Date 2019/8/23 10:26
 */
@Controller
@AllArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping({"/", "/index", "/home"})
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(UserDO userDO) {
        // 此处省略校验逻辑
        if (userService.insert(userDO))
            return "redirect:register?success";
        else
            return "redirect:register?error";
    }
}
