package com.schening.phoenix.security.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author schening
 * @date 2020/7/11
 */
@RestController
public class LoginResource {

    /**
     * 用户登录成功
     *
     * @return
     */
    @RequestMapping(value = "/login-success")
    public String loginSuccess() {
        String username = getUserName();
        return username + "登录成功";
    }

    /**
     * 测试资源1
     *
     * @return
     */
    @GetMapping(value = "/r/r1")
    public String r1() {
        String username = getUserName();
        return username + "访问资源1";
    }

    /**
     * 测试资源2
     *
     * @return
     */
    @GetMapping(value = "/r/r2")
    public String r2() {
        String username = getUserName();
        return username + "访问资源2";
    }

    /**
     * 测试资源3
     *
     * @return
     */
    @GetMapping(value = "/r/r3")
    public String r3() {
        String username = getUserName();
        return username + "访问资源3";
    }

    /**
     * 获取当前登录用户名
     *
     * @return
     */
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        String username = null;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }

}
