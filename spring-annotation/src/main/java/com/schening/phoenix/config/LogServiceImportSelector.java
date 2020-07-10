package com.schening.phoenix.config;

import com.schening.phoenix.service.LogService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 一个 ImportSelector 实现类
 * 用于演示通过 ImportSelector ，给定一组要注册为bean组件的普通Java类的名称，
 * 然后通过利用 @Import(LogServiceImportSelector.class)即可注册相应的bean
 *
 * @author schening
 * @date 2020/7/9
 */
public class LogServiceImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 返回要注册成为 bean 的类的全名称的数组
        return new String[]{LogService.class.getName()};
    }
}