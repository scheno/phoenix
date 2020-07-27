package com.schening.phoenix.security.web.impl;

import com.schening.phoenix.security.web.GoodsResource;
import com.schening.phoenix.security.web.LoginResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author schening
 * @date 2020/7/13
 */
@RestController
public class GoodsResourceImpl implements GoodsResource {

    @Resource
    private LoginResource loginResource;

    private final static String PREFIX = "/goods";

    @Override
    @GetMapping(PREFIX)
    public String listOrders() {
        return loginResource.getUserName() + "查询货物信息";
    }

}
