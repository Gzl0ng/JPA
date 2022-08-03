package com.gzl0ng.test;

import com.gzl0ng.config.SpringDataJPAConfig;
import com.gzl0ng.pojo.Customer;
import com.gzl0ng.repositories.CustomerSpecificationsRepository;
import org.apache.openjpa.lib.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-08-03
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpecificationsTest {

    //jdk动态代理的实例
    @Autowired
    private CustomerSpecificationsRepository repository;

    //如果要实现分组查询
    @Autowired
    EntityManager entityManager;

    @Test
    public void testR(){

        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //root from Customer   ,  获取列
                //CriteriaBuilder  where 设置各种条件  (> < in ...)
                //query  组合 (order by,where)

                return null;
            }
        });
    }

    /**
     * 查询客户范围(in)
     * id >大于
     * 地址精确
     */
    @Test
    public void testR2(){
        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //root from Customer   ,  获取列
                //CriteriaBuilder  where 设置各种条件  (> < in ...)
                //query  组合 (order by,where)
                Path<Object> custId = root.get("custId");
                Path<Object> custName = root.get("custName");
                Path<Object> custAddress = root.get("custAddress");

                //参数1：为哪个字段设置条件         参数2：值
                Predicate pr = cb.equal(custAddress, "BEIJING");

                return pr;
            }
        });

        System.out.println(customers);
    }

    /**
     * 查询客户范围(in)
     * id >大于
     * 地址精确
     */
    @Test
    public void testR3(){
        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //root from Customer   ,  获取列
                //CriteriaBuilder  where 设置各种条件  (> < in ...)
                //query  组合 (order by,where)
                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAddress");

                //参数1：为哪个字段设置条件         参数2：值
                Predicate custAddressP = cb.equal(custAddress, "BEIJING");
                Predicate custIdp = cb.greaterThan(custId, 5L);
                CriteriaBuilder.In<String> in = cb.in(custName);
                CriteriaBuilder.In<String> custNameP = in.value("牛逼").value("李四");

                Predicate and = cb.and(custAddressP, custIdp,custNameP);
                return and;
            }
        });

        System.out.println(customers);
    }

    /**
     * 查询客户范围(in)
     * id >大于
     * 地址精确
     */
    @Test
    public void testR4(){
        //模拟传参
        Customer params = new Customer();
//        params.setCustAddress("BEIJING");
        params.setCustId(0L);
        params.setCustName("牛逼,李四");

        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //root from Customer   ,  获取列
                //CriteriaBuilder  where 设置各种条件  (> < in ...)
                //query  组合 (order by,where)
                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAddress");

                //参数1：为哪个字段设置条件         参数2：值
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(params.getCustAddress())){
                    Predicate custAddressP = cb.equal(custAddress, params.getCustAddress());
                    list.add(custAddressP);
                }
                if (params.getCustId()>-1){
                    Predicate custIdP = cb.greaterThan(custId, params.getCustId());
                    list.add(custIdP);
                }
                if (!StringUtils.isEmpty(params.getCustName())){
                    CriteriaBuilder.In<String> custNameP = cb.in(custName);
                    for (String s : params.getCustName().split(",")) {
                        custNameP.value(s);
                    }
                    list.add(custNameP);
                }

                Predicate and = cb.and(list.toArray(new Predicate[list.size()]));
                return and;
            }
        });

        System.out.println(customers);
    }

    @Test
    public void testR5(){
        //如果要分组或者挑字段
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> query = builder.createQuery();
        Root<Customer> root = query.from(Customer.class);
        query.multiselect();


        //模拟传参
        Customer params = new Customer();
//        params.setCustAddress("BEIJING");
        params.setCustId(0L);
        params.setCustName("牛逼,李四");

        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //root from Customer   ,  获取列
                //CriteriaBuilder  where 设置各种条件  (> < in ...)
                //query  组合 (order by,where)

                //1.通过root拿到需要设置条件的字段
                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAddress");

                //2.通过CriteriaBuilder设置不同类型条件
                //参数1：为哪个字段设置条件         参数2：值
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(params.getCustAddress())){
                    Predicate custAddressP = cb.equal(custAddress, params.getCustAddress());
                    list.add(custAddressP);
                }
                if (params.getCustId()>-1){
                    Predicate custIdP = cb.greaterThan(custId, params.getCustId());
                    list.add(custIdP);
                }
                if (!StringUtils.isEmpty(params.getCustName())){
                    CriteriaBuilder.In<String> custNameP = cb.in(custName);
                    for (String s : params.getCustName().split(",")) {
                        custNameP.value(s);
                    }
                    list.add(custNameP);
                }

                //3.组合条件，需要排序就需要query进行组合
                Predicate and = cb.and(list.toArray(new Predicate[list.size()]));
                Order desc = cb.desc(custId);

//                Predicate pr = query.multiselect().groupBy().where(and).orderBy(desc).getRestriction();//这里已经定死了，无法选择列和分组


                Predicate pr = query.where(and).orderBy(desc).getRestriction();
                return pr;
            }
        });

        System.out.println(customers);
    }
}
