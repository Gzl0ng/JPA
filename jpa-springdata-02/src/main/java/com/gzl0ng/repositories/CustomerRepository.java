package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

    //增删改查

    //查询
    @Query("from Customer where custName = ?1")
    List<Customer> findCustomerByCustName(String custName);

    @Query("from Customer where custId = :custId")
    List<Customer> findCustomerByCustId(@Param("custId") Long custId);

    //修改
    @Transactional  //通常写在事务逻辑层
    @Modifying      //通知springdataJpa是增删改操作
    @Query("UPDATE Customer c set c.custName=:custName where c.custId=:custId")
    int updateCustomer(@Param("custName") String custName,@Param("custId") Long custId);

    //删除
    @Transactional
    @Modifying
    @Query("DELETE from Customer c where c.custId=?1")
    int deleteCustomer(Long custId);

    //伪增加
    @Transactional
    @Modifying
    @Query("insert into Customer(custName) select c.custName from Customer c where c.custId=?1")
    int insertCustomerBySelect(Long id);


    //原生SQL
    @Query(value = "select * from customer where cust_name=:custName",nativeQuery = true)
    List<Customer> findCustomerByCustNameBySql(@Param("custName") String custName);
}
