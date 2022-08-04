package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
}
