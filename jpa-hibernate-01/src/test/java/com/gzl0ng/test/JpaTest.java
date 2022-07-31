package com.gzl0ng.test;

import com.gzl0ng.pojo.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */
public class JpaTest {
    EntityManagerFactory factory;

    @Before
    public void before(){
        factory = Persistence.createEntityManagerFactory("hibernateJPA");
    }



    @Test
    public void testC(){
            EntityManager entityManager = factory.createEntityManager();

            EntityTransaction tx = entityManager.getTransaction();

            tx.begin();

            Customer customer = new Customer();
            customer.setCustName("张三");
            entityManager.persist(customer);

            tx.commit();
    }

    //立即查询
    @Test
    public void testR(){
        EntityManager entityManager = factory.createEntityManager();

        //事务开启
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println(customer);

        //事务结束
        tx.commit();
    }

    //延迟查询
    @Test
    public void testR_r(){
        EntityManager entityManager = factory.createEntityManager();

        //事务开启
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Customer customer = entityManager.getReference(Customer.class, 1L);
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println(customer);

        //事务结束
        tx.commit();
    }

    @Test
    public void testU(){
        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = new Customer();
        customer.setCustId(222L);
        customer.setCustName("王五");

        //如果指定了主键：
        // 更新：1.先查询 看是否有变化
        /*
        * 1.先查询 看是否变化，如果有变化就修改,如果查出来指定位置为空也会插入(不会插入到主键查询位置)
        * 2.不指定，就会直接插入*/
        em.merge(customer);

        tx.commit();
    }

    @Test
    public void testHql(){
        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpal = "UPDATE Customer set custName=:name where custId=:id";
        em.createQuery(jpal)
        .setParameter("name","李珊珊")
                .setParameter("id",8L)
                        .executeUpdate();

        tx.commit();
    }

    @Test
    public void testD(){
        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //先查后删除，或者自己定义SQL
        Customer customer = em.find(Customer.class, 1L);
        em.remove(customer);

        tx.commit();
    }

    @Test
    public void testStatus(){

        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //临时状态
        Customer customer = new Customer();

        //游历状态,会报错
//        customer.setCustId(6L);

        //持久状态
        Customer customer1 = em.find(customer.getClass(), 1L);

        //删除状态
        em.remove(customer);

        tx.commit();
    }

    @Test
    public void testStatus02(){

        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //持久状态
        Customer customer = em.find(Customer.class,2L);

        customer.setCustName("牛逼");

        System.out.println(customer);

        tx.commit();
    }

    //同一个entityManager的二次相同查询会缓存，不会查二次
    @Test
    public void testCache(){

        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = em.find(Customer.class,2L);
        Customer customer2 = em.find(Customer.class,2L);

        tx.commit();




//        EntityManager em2 = factory.createEntityManager();
//        EntityTransaction tx2 = em2.getTransaction();
//        tx2.begin();
//        Customer customer1 = em2.find(Customer.class, 2L);
//
//        tx2.commit();
    }
}
