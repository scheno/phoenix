package com.schening.phoenix.security.web.impl;

import com.schening.phoenix.security.po.OrderPO;
import com.schening.phoenix.security.web.LoginResource;
import com.schening.phoenix.security.web.OrderResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author schening
 * @date 2020/7/13
 */
@RestController
public class OrderResourceImpl implements OrderResource {

    @Resource
    private LoginResource loginResource;

    private final static String PREFIX = "/order";

    @Override
    @GetMapping(PREFIX)
    public String listOrders() {
        return loginResource.getUserName() + "查询订单信息";
    }

    @Override
    @GetMapping(PREFIX + "/{no}")
    public OrderPO getOrder(@PathVariable("no") String no) {
        OrderPO orderPO = new OrderPO();
        orderPO.setNo("1");
        orderPO.setName("订单1");
        return orderPO;
    }

}
