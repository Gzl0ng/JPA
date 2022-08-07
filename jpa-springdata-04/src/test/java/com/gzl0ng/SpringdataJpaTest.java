package com.gzl0ng;

import com.gzl0ng.config.SpringDataJPAConfig;
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
 * @date 2022-08-07
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringdataJpaTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testR(){
        Optional<Customer> byId = repository.findById(3L);

        System.out.println(byId.get());
    }
}
