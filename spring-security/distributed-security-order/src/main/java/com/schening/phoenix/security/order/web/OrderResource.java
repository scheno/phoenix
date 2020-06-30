package com.schening.phoenix.security.order.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author schening
 * @date 2020/6/30
 */
@RestController
public class OrderResource {


    /**
     *
     * @return
     */
    @GetMapping(value = "/r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1() {
        return "访问资源1";
    }
}
