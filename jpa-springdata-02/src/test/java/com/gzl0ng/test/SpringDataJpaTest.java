package com.gzl0ng.test;

import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJpaTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void test(){
        Optional<Customer> byId = repository.findById(2L);

        System.out.println(byId.get());
    }
}
