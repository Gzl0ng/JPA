package com.gzl0ng.test;

import com.gzl0ng.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */
public class HibernateTest {

    //session工厂  Session:数据库会话  代码持久化操作数据库的桥梁
    private SessionFactory sf;

    @Before
    public void init(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();

        //2.根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的session工厂

        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testC(){
        //进行持久化操作
        try (Session session =sf.openSession()){

            Transaction tx = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustName("郭正龍");

            session.save(customer);

            tx.commit();
        }
    }


    @Test
    public void testR(){
        //进行持久化操作
        try (Session session =sf.openSession()){

            Transaction tx = session.beginTransaction();

            Customer customer = session.find(Customer.class, 1L);
            //懒加载，实际使用时去查询
//            Customer customer = session.load(Customer.class, 1L);
            System.out.println(customer);

            tx.commit();
        }
    }

    @Test
    public void testU(){
        //进行持久化操作
        try (Session session =sf.openSession()){

            Transaction tx = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustId(3L);//不设置会去插入，设置了会去更新对应主键的一行数据
            customer.setCustName("郭正龙");

//            session.save()
//            session.update();
            session.saveOrUpdate(customer);
            tx.commit();
        }
    }

    @Test
    public void testD(){
        //进行持久化操作
        try (Session session =sf.openSession()){

            Transaction tx = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustId(1L);

            /*
            * save
            * update
            * remove
            * saveOrUpdate
            * */
            session.remove(customer);

            tx.commit();
        }
    }

    //HQL
    @Test
    public void testR_Hql(){
        //进行持久化操作
        try (Session session =sf.openSession()){

            Transaction tx = session.beginTransaction();

            String hql = " FROM Customer where custId=:id";

            List<Customer> resultList = session.createQuery(hql, Customer.class)
                    .setParameter("id",2L)
                    .getResultList();

            System.out.println(resultList);

            tx.commit();
        }
    }
}
