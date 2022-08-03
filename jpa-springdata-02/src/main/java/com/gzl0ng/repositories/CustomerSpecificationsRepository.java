package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author 郭正龙
 * @date 2022-08-03
 */
public interface CustomerSpecificationsRepository extends PagingAndSortingRepository<Customer,Long>,
        JpaSpecificationExecutor<Customer> {


}
