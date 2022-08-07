package com.gzl0ng.gzl;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 郭正龙
 * @date 2022-08-06
 */
public class MyJpaRepository implements InvocationHandler {

    EntityManager em;
    Class pojoClass;

    public MyJpaRepository(EntityManager em, Class pojoClass) {
        this.em = em;
        this.pojoClass = pojoClass;
    }

    //method  当前调用的方法   = findById
    //args 当前调用方法的参数  =1
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("查询");

        //Jpa同统一实现类
        MyJpaProxy myJpaProxy = new MyJpaProxy(em, pojoClass);

        //使用的方法名
        String name = method.getName();

        Method JpaMethod = myJpaProxy.getClass().getMethod(method.getName(), method.getParameterTypes());


        return JpaMethod.invoke(myJpaProxy,args);
    }
}
