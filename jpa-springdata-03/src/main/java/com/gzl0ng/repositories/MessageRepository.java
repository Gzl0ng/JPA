package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import com.gzl0ng.pojo.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-08-06
 */
public interface MessageRepository extends PagingAndSortingRepository<Message,Long> {

    //根据客户id查询所有信息
    //通过归档方法名来实现关联查询：需要通过关联属性来进行匹配
    //但是只能通过id来进行匹配
    List<Message> findByCustomer(Customer customer);
}
