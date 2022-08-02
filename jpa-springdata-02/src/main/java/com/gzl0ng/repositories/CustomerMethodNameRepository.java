package com.gzl0ng.repositories;

import com.gzl0ng.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-08-02
 */
public interface CustomerMethodNameRepository extends PagingAndSortingRepository<Customer,Long> {

    List<Customer> findByCustName(String custName);

    boolean existsByCustName(String custName);

    @Transactional
    @Modifying
    int deleteByCustId(Long id);

    List<Customer> findByCustNameLike(String custName);
}
