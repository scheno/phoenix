package com.schening.phoenix;

import com.schening.phoenix.config.EnableMyOwnBeanDefinitions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 演示自定义注解@EnableMyOwnBeanDefinitions的使用和效果
 * AdminService|CustomerService由{@link org.springframework.context.annotation.Configuration}和{@link org.springframework.context.annotation.Bean}注册
 * LoginService通过实现{@link org.springframework.context.annotation.ImportSelector}注册
 * OrderService|OrderChangeRecordService通过实现{@link org.springframework.context.annotation.ImportBeanDefinitionRegistrar}实现
 *
 * @author schening
 * @date 2020/7/9
 */
@EnableMyOwnBeanDefinitions(trackOrderChange = true)
//@EnableMyOwnBeanDefinitions(trackOrderChange = false)
@SpringBootApplication
public class EnableAnnotationApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(EnableAnnotationApplication.class, args);
        dumpBeansToConsole(applicationContext);
    }


    /**
     * 往控制台上输出容器中注册的各个bean的名称
     *
     * @param applicationContext
     */
    private static void dumpBeansToConsole(ConfigurableApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            Object bean = applicationContext.getBean(name);
            System.out.printf("%s[%s]\n", name, bean.getClass().getName());
        }
    }
}