package com.gzl0ng.gzl.v2;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 郭正龙
 * @date 2022-08-07
 *
 * 自定义的扫描器
 *
 * 原有的不变
 *
 * 原有的，自己通过这个扫描器进行动态注册
 */
public class JpaClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public JpaClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry){
        super(registry);
    }

    //如果他是一个接口就视为有效组件,必须实现了Repository
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isInterface();
    }
}
