package com.gzl0ng.gzl;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author 郭正龙
 * @date 2022-08-06
 */
public class MainStart2 {
    public static void main(String[] args) throws ClassNotFoundException {
        //spring上下文  spring容器  --> ioc加载过程：创建所有的bean 包括Repository的Bean
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataJPAConfig.class);

        //spring容器创建
        CustomerRepository repository = context.getBean(CustomerRepository.class);
        System.out.println(repository.getClass());  //jdk动态代理
        Optional<Customer> byId = repository.findById(2L);

        System.out.println(byId.get());
    }
}
