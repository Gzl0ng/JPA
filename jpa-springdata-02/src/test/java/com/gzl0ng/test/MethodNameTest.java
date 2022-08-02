package com.gzl0ng.test;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerMethodNameRepository;
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
 * @date 2022-08-02
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MethodNameTest {

    @Autowired
    CustomerMethodNameRepository repository;

    @Test
    public void test01(){
        List<Customer> custName = repository.findByCustName("牛逼");
        System.out.println(custName);
    }

    @Test
    public void test02(){
        boolean custName = repository.existsByCustName("牛逼1");
        System.out.println(custName);
    }


    @Test
    public void test03(){
        int custName = repository.deleteByCustId(4L);
        System.out.println(custName);
    }

    //模糊查询
    @Test
    public void test04(){
        List<Customer> custNameLike = repository.findByCustNameLike("牛%");
        System.out.println(custNameLike);
    }
}
