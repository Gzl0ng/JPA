package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */
public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
