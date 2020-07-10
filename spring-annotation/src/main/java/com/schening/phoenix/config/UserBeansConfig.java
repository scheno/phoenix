package com.schening.phoenix.config;

import com.schening.phoenix.service.AdminService;
import com.schening.phoenix.service.CustomerService;
import org.springframework.context.annotation.Bean;

/**
 * 一个 Spring 配置类， 注解方式注册bean的典型用法 ：
 *
 * @Configuration 定义配置类 + @Bean 注解配置类方法注册bean
 *
 * @author schening
 * @date 2020/7/9
 */
//@Configuration
public class UserBeansConfig {
    /**
     * 注册管理员管理服务组件bean
     * AdminService 是一个普通Java类
     *
     * @return
     */
    @Bean
    public AdminService adminService() {
        return new AdminService();
    }

    /**
     * 注册客户管理服务组件bean
     * CustomerService 是一个普通Java类
     *
     * @return
     */
    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }
}