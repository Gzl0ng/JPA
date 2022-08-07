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
public class MainStart {
    public static void main(String[] args) throws ClassNotFoundException {
        //spring上下文  spring容器  --> ioc加载过程：创建所有的bean 包括Repository的Bean
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataJPAConfig.class);

        //spring容器创建
//        CustomerRepository repository = context.getBean(CustomerRepository.class);
//        System.out.println(repository.getClass());  //jdk动态代理
//        Optional<Customer> byId = repository.findById(2L);
//
//        System.out.println(byId.get());



        //获得EntityManager
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = context.getBean(LocalContainerEntityManagerFactoryBean.class);
        EntityManager entityManager = entityManagerFactoryBean.createNativeEntityManager(null);
        //getGenericInterfaces获得当前接口的pojo类的父接口(PagingAndSortingRepository),这里只有一个pojo类直接指定0号数组
        ParameterizedType parameterizedType = (ParameterizedType) CustomerRepository.class.getGenericInterfaces()[0];
        //能拿到接口的泛型 <Customer,Long>
        Type type = parameterizedType.getActualTypeArguments()[0];
        Class clazz = Class.forName(type.getTypeName());

        //自己创建
        CustomerRepository repository = (CustomerRepository) Proxy.newProxyInstance(
                CustomerRepository.class.getClassLoader(),
                new Class[]{CustomerRepository.class},
                new MyJpaRepository(entityManager,clazz)

        );

        System.out.println(repository.findById(3L).get());
    }
}
