package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */
@Component
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
}
