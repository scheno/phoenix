package com.schening.phoenix.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author schening
 * @date 2020/6/29
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 默认Url根路径跳转到/login，此url为spring security提供
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login");
    }
}
