package com.gzl0ng.test;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerQBExampleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-08-02
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QBExampleTest {

    @Autowired
    CustomerQBExampleRepository repository;

    /**
     * 简单实例  客户名称  客户地址动态查询
     */
    @Test
    public void test01(){
        //查询条件
        Customer customer = new Customer();
        customer.setCustName("牛逼");
        customer.setCustAddress("北京");

        //构建查询条件
        Example<Customer> example = Example.of(customer);

        List<Customer> customers = (List<Customer>) repository.findAll(example);
        System.out.println(customers);
    }

    /**
     * 通过匹配器进行条件的限制
     * 简单实例  客户名称  客户地址动态查询
     */
    @Test
    public void test02(){
        //查询条件
        Customer customer = new Customer();
        customer.setCustName("逼");
        customer.setCustAddress("jing");

        //通过匹配器 对条件行为进行设置
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("custName")//设置忽略的属性
                .withIgnoreCase("custAddress")//设置忽略大小写
//                .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);//对所有字符串的匹配进行模糊匹配
//                .withMatcher("custAddress",m->m.endsWith())//和下面是一样
                .withMatcher("custAddress", ExampleMatcher.GenericPropertyMatchers.endsWith().ignoreCase());//针对单个条件进行限制

        Example<Customer> example = Example.of(customer,matcher);

        List<Customer> customers = (List<Customer>) repository.findAll(example);
        System.out.println(customers);
    }
}
