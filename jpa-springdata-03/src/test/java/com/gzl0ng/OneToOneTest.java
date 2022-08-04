package com.gzl0ng;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Account;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author 郭正龙
 * @date 2022-08-04
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToOneTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testC(){
        Account account = new Account();
        account.setUsername("niubi");

        Customer customer = new Customer();
        customer.setCustName("牛逼");
        customer.setAccount(account);

        repository.save(customer);
    }

    //查询
    @Test
    //为什么懒加载要配置事务
    //当通过repository调用完查询方法，session就会立即关闭，一旦关闭session就不能查询关联表
    //加了事务后就能让session直到事务方法执行完毕后才会关闭
    @Transactional(readOnly = true)
    public void testR(){
        Optional<Customer> byId = repository.findById(1L);
        System.out.println("==================");
        System.out.println(byId);
    }

    //删除
    @Test
    public void testD(){

        repository.deleteById(2L);
    }

    //修改
    @Test
    public void testU(){
        Customer customer = new Customer();
        customer.setCustId(6L);
        customer.setCustName("李四");
        customer.setAccount(null);
        repository.save(customer);
    }
}
