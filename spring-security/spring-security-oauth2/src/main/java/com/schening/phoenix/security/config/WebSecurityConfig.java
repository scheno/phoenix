package com.schening.phoenix.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security核心配置类
 * <p>自定义安全拦截机制</p>
 * <p>参考文章：https://blog.csdn.net/andy_zhang2007/article/details/90632411</p>
 *
 * @author schening
 * @date 2020/6/29
 */
@Order(2)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用来配置拦截保护的请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**")
                .and().authorizeRequests().antMatchers("/oauth/*").authenticated()
                .and().formLogin().successForwardUrl("/login-success").permitAll();
    }

}
