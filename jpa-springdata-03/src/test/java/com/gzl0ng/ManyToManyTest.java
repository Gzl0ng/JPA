package com.gzl0ng;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.pojo.Role;
import com.gzl0ng.repositories.CustomerRepository;
import com.gzl0ng.repositories.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author 郭正龙
 * @date 2022-08-06
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToManyTest {
    @Autowired
    CustomerRepository repository;

    @Autowired
    RoleRepository roleRepository;

    /**
     * 1.如果保存的关联数据  希望所有已有的，需要从数据库中查出来（持久状态）。否则提升游离状态不能持久化
     * 2.如果一个业务方法有多个吃好操作，记得加上@Transactional，否则不能共用一个session
     * 3.在单元测试用到了@Transactional，如果有增删改一定要加，这是为单元测试提供的(不希望数据入库)
     * 4.单元测试会认为你的事务方法@Transactional，只是测试而已，它不会为你提交事务
     */
    @Test
    @Transactional
    @Commit
    public void testC(){
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("超级管理员"));
        roles.add(new Role("商品管理员"));


        Customer customer = new Customer();
        customer.setCustName("牛逼");
        customer.setRoles(roles);

        repository.save(customer);
    }

    @Test
    @Transactional(readOnly = true)  //把下面单作一整个方法
    public void testR(){
        Optional<Customer> byId = repository.findById(1L);

        System.out.println(byId);
    }

    @Test
    @Transactional
    @Commit
    public void testD(){
        Optional<Customer> byId = repository.findById(2L);
        repository.delete(byId.get());
    }
}
