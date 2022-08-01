package com.gzl0ng.test;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-08-01
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpqlTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testR(){
        List<Customer> custName = repository.findCustomerByCustName("牛逼");
        System.out.println(custName);

        List<Customer> custId = repository.findCustomerByCustId(4L);
        System.out.println(custId);
    }


    @Test
    public void testU(){
        int r = repository.updateCustomer("牛逼", 6L);
        System.out.println(r);
    }

    @Test
    public void testD(){
        int i = repository.deleteCustomer(8L);
        System.out.println(i);
    }

    @Test
    public void testC(){
        int i = repository.insertCustomerBySelect(7L);
        System.out.println(i);
    }


    @Test
    public void testR_2(){
        List<Customer> customer = repository.findCustomerByCustNameBySql("牛逼");
        System.out.println(customer);
    }
}
