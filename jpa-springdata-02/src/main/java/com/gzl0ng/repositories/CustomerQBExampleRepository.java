package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author 郭正龙
 * @date 2022-08-02
 */
public interface CustomerQBExampleRepository extends PagingAndSortingRepository<Customer,String>, QueryByExampleExecutor<Customer> {

//    QueryByExampleExecutor也支持分页和排序查询，还有统计个数和是否存在,和PagingAndSortingRepository一样传入对应参数就可以


}
