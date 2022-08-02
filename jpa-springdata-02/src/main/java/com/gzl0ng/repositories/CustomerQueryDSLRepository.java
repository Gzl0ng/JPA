package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author 郭正龙
 * @date 2022-08-02
 */
public interface CustomerQueryDSLRepository extends PagingAndSortingRepository<Customer,String>,
        QuerydslPredicateExecutor<Customer> {


}
