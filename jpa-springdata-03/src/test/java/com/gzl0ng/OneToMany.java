package com.gzl0ng;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.pojo.Message;
import com.gzl0ng.repositories.CustomerRepository;
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
public class OneToMany {
    @Autowired
    CustomerRepository repository;

    //插入
    @Test
    public void testC(){
        ArrayList<Message> messageList = new ArrayList<>();
        messageList.add(new Message("sb"));
        messageList.add(new Message("sbbb"));

        Customer customer = new Customer();
        customer.setCustName("秒啊");
        customer.setMessages(messageList);

        repository.save(customer);
    }

    @Test
    @Transactional(readOnly = true)
    public void testR(){
        //懒加载过程
        //1.findById  只会查询Customer 和其他关联的立即加载
        Optional<Customer> byId = repository.findById(1L);
        System.out.println("=============");
        //由于进行了输出，会自动调用customer.toString
        System.out.println(byId);
    }

    @Test
    public void testD(){
        repository.deleteById(3L);
    }

    @Test
    @Transactional
    @Commit
    public void testU(){
        Optional<Customer> customer = repository.findById(5L);
        customer.get().setCustName("xxxx");
    }
}
