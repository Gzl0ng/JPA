package com.gzl0ng.test;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */

//@ContextConfiguration("/spring.xml")    xml方式配置
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJpaTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void test(){
        Optional<Customer> byId = repository.findById(2L);

        System.out.println(byId.get());
    }

    @Test
    public void testAll(){

        Iterable<Customer> allById = repository.findAllById(Arrays.asList(2L, 3L, 4L, 6L, 7L));

        System.out.println(allById);
    }
}
