package com.schening.phoenix.config;

import com.schening.phoenix.service.OrderChangeRecordService;
import com.schening.phoenix.service.OrderService;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * 一个接口ImportBeanDefinitionRegistrar的实现类，
 * 用于演示 , 演示点:
 * 1. 程序化将普通Java类作为bean注册到Spring IoC容器;
 * 2. 使用注解元数据属性动态决定bean的注册;
 *
 * @author schening
 * @date 2020/7/9
 */
public class OrderServiceBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 注解元数据
     * @param registry               Spring IoC 容器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(
                        EnableMyOwnBeanDefinitions.class.getName()));

        {// 注册类型为 OrderService 的订单服务组件 bean
            Class beanClass = OrderService.class;
            RootBeanDefinition beanDefinition = new RootBeanDefinition(beanClass);
            String beanName = StringUtils.uncapitalize(beanClass.getSimpleName());
            registry.registerBeanDefinition(beanName, beanDefinition);
        }

        {// 看情况决定是否注册类型为 OrderChangeRecordService 的订单变更记录服务组件 bean
            boolean trackOrderChange = annotationAttributes.getBoolean("trackOrderChange");
            if (trackOrderChange) {
                Class beanClass = OrderChangeRecordService.class;
                RootBeanDefinition beanDefinition = new RootBeanDefinition(beanClass);
                String beanName = StringUtils.uncapitalize(beanClass.getSimpleName());
                registry.registerBeanDefinition(beanName, beanDefinition);
            }
        }
    }
}