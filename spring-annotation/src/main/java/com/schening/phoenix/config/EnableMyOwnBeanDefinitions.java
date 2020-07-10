package com.schening.phoenix.config;

import com.schening.phoenix.service.SettingService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模仿 Spring 框架的 @EnableXXX 注解自定义的一个 @Enable 注解，
 * 此类注解一般使用 @Import 通过以下四种方式进行 bean 定义：
 * 1. @Configuration 注解的专门用于bean定义的类,一般通过@Bean注解的方法注册bean ;
 * 2. ImportSelector 给出某些要注册为bean的普通类的类名，将它们注册为 bean ;
 * 3. ImportBeanDefinitionRegistrar 直接基于某些普通类创建 BeanDefinition 并注册相应的 bean ,可以有较复杂的逻辑;
 * 4. 直接将某个普通类作为一个bean注册到容器。
 *
 * @author schening
 * @date 2020/7/9
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({
        UserBeansConfig.class,
        LogServiceImportSelector.class,
        OrderServiceBeanDefinitionRegistrar.class,
        SettingService.class})
public @interface EnableMyOwnBeanDefinitions {
    /**
     * 注解属性：是否记录订单变更，语义 ：
     * false -- 不注册订单变更记录服务组件bean
     * true -- 注册订单变更记录服务组件bean
     *
     * @return
     */
    boolean trackOrderChange() default false;
}