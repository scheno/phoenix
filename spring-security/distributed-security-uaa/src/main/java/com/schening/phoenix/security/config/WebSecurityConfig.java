package com.schening.phoenix.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>自定义安全拦截机制</p>
 * <p>参考文章：https://blog.csdn.net/andy_zhang2007/article/details/90632411</p>
 *
 * @author schening
 * @date 2020/6/29
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


}
