package com.gzl0ng.test;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.pojo.QCustomer;
import com.gzl0ng.repositories.CustomerQueryDSLRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author 郭正龙
 * @date 2022-08-02
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryDSLTest {

    @Autowired
    CustomerQueryDSLRepository repository;

    @Test
    public void test01(){
        QCustomer customer = QCustomer.customer;

        //通过id查找
        BooleanExpression eq = customer.custId.eq(2L);
        Optional<Customer> one = repository.findOne(eq);
        System.out.println(one);

    }

    /**
     * 查询客户名称范围（in）
     * id > 大于
     * 地址  精确
     */
    @Test
    public void test02(){
        QCustomer customer = QCustomer.customer;

        //通过id查找
        BooleanExpression and = customer.custName.in("牛逼", "王五")
                .and(customer.custId.gt(2))
                .and(customer.custAddress.eq("beijing"));

        System.out.println(repository.findAll(and));

    }

    @Test
    public void test03(){

        Customer params = new Customer();
        params.setCustAddress("BEIJING");
        params.setCustId(9L);
        params.setCustName("牛逼,李四");


        QCustomer customer = QCustomer.customer;

        //初始条件 类似于1=1   永远都成立的条件
        BooleanExpression expression = customer.isNotNull().or(customer.isNull());

        expression=params.getCustId()>-1 ?
                expression.and(customer.custId.gt(params.getCustId())):expression;
        expression= !StringUtils.isEmpty(params.getCustName()) ?
                expression.and(customer.custName.in(params.getCustName().split(","))):expression;
        expression= !StringUtils.isEmpty(params.getCustAddress()) ?
                expression.and(customer.custAddress.eq(params.getCustAddress())):expression;


        System.out.println(repository.findAll(expression));

    }

    //用Autowired注解线程安全问题，PersistenceContext是单独为entityManager设计出来的
    @PersistenceContext
    EntityManager entityManager;
    /**
     * 自定义列查询，分组
     * 需要使用原生态的方式（Specification）
     * 通过Repository进行查询，列，表都是固定的
     */
    @Test
    public void test04(){

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QCustomer customer = QCustomer.customer;

//        JPAQuery<Customer> customerJPAQuery = queryFactory.select(customer)
//                .from(customer)
//                .where(customer.custId.eq(1L))
//                .orderBy(customer.custId.asc());
        //select id,custName
        JPAQuery<Tuple> tupleJPAQuery = queryFactory.select(customer.custId, customer.custName)
                .from(customer)
                .where(customer.custId.eq(2L))
                .orderBy(customer.custId.asc());

        //fetch执行查询
        List<Tuple> fetch = tupleJPAQuery.fetch();
        for (Tuple tuple : fetch) {
            System.out.println(tuple.get(customer.custId));
            System.out.println(tuple.get(customer.custName));
        }

        JPAQuery<Long> qery = queryFactory.select(customer.custId.sum())
                .from(customer)
                .where(customer.custId.gt(7L));

        List<Long> fetch1 = qery.fetch();
        for (Long aLong : fetch1) {
            System.out.println(aLong);
        }

    }
}
