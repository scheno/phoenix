package com.schening.phoenix.security.order.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author schening
 * @date 2020/6/30
 */
@RestController
public class OrderResource {

    /**
     * 拥有p1权限方可访问此url
     *
     * @return
     */
    @GetMapping(value = "/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1(){
        // 获取用户身份信息
        String fullname =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return fullname + "访问资源1";
    }

}
