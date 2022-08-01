package com.gzl0ng.test;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 郭正龙
 * @date 2022-08-01
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJpaPageAndSortTest {

    @Autowired
    CustomerRepository repository;


    @Test
    public void testPageing(){
        Page<Customer> all = repository.findAll(PageRequest.of(0, 2));
        System.out.println(all.getTotalPages());//总页数
        System.out.println(all.getTotalElements());//元素总数
        System.out.println(all.getContent());//当前页的数据
    }

    @Test
    public void testSort(){
        Sort sort = Sort.by("custId").descending();
        Iterable<Customer> all = repository.findAll(sort);
        System.out.println(all);
    }

    @Test
    public void testSortTypeSafe(){
        Sort.TypedSort<Customer> custome = Sort.sort(Customer.class);

        Sort sort = custome.by(Customer::getCustId).descending();
//                .and(custome.by(Customer::getCustName).descending());

        Iterable<Customer> all = repository.findAll(sort);
        System.out.println(all);
    }
}
