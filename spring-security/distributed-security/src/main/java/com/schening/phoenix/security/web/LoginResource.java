package com.schening.phoenix.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author schening
 * @date 2020/6/29
 */
@RestController
public class LoginResource {

    @PostMapping(value = "/login‐success")
    public String loginSuccess() {
        return " 登录成功";
    }

    /**
     * 测试资源1
     *
     * @return
     */
    @GetMapping(value = "/r/r1")
    public String r1() {
        return " 访问资源1";
    }

    /**
     * 测试资源2
     *
     * @return
     */
    @GetMapping(value = "/r/r2")
    public String r2() {
        return " 访问资源2";
    }

    /**
     * 测试资源3
     *
     * @return
     */
    @GetMapping(value = "/e/e1")
    public String e1() {
        return " 访问其它资源1";
    }


}
