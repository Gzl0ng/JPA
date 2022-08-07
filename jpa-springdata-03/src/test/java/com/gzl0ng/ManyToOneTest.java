package com.gzl0ng;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.pojo.Message;
import com.gzl0ng.repositories.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-08-06
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToOneTest {

    @Autowired
    MessageRepository repository;

    //多对一  插入
    //得出： 当插入“多”的数据的时候，使用多对一的关联关系是更合理的
    @Test
    public void testC(){
        //一
        Customer customer = new Customer();
        customer.setCustName("sb");

        //多
        List<Message> list = new ArrayList<>();

        list.add(new Message("你好",customer));
        list.add(new Message("好不好",customer));

        repository.saveAll(list);
    }

    //根据客户的id查询对应的所有信息，查询
    //通过“一”进行条件查询，在一对多中实现是更合理的
    @Test
    public void testR(){
        Customer customer = new Customer();
        customer.setCustId(1L);
        customer.setCustName("牛逼");


        List<Message> messages = repository.findByCustomer(customer);
        //隐式调用toString()
        System.out.println(messages);
    }

    @Test
    public void testD(){
        Customer customer = new Customer();
        customer.setCustId(2L);

        List<Message> messages = repository.findByCustomer(customer);

        repository.deleteAll(messages);

    }
}
