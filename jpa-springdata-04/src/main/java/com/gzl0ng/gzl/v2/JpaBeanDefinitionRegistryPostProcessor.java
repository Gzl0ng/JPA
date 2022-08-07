package com.gzl0ng.gzl.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author 郭正龙
 * @date 2022-08-07
 *
 * @Import
 */
@Component
public class JpaBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        //动态注册了
        JpaClassPathBeanDefinitionScanner jpaClassPathBeanDefinitionScanner = new JpaClassPathBeanDefinitionScanner(beanDefinitionRegistry);

        //限制必须实现Repository接口
        jpaClassPathBeanDefinitionScanner.addIncludeFilter(new AssignableTypeFilter(Repository.class));

        jpaClassPathBeanDefinitionScanner.scan("com.gzl0ng.repositories");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
