package com.schening.phoenix.security.web;

import com.schening.phoenix.security.po.OrderPO;

/**
 * @author schening
 * @date 2020/7/13
 */
public interface OrderResource {

    /**
     * 订单查询
     * @return
     */
    String listOrders();

    /**
     * 订单详情查询
     * @param no
     * @return
     */
    OrderPO getOrder(String no);

}
