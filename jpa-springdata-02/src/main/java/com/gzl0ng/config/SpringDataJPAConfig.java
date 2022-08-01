package com.gzl0ng.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author 郭正龙
 * @date 2022-08-01
 */
@Configuration    //标记当前类为配置类  =xml配置文件
@EnableJpaRepositories(basePackages = "com.gzl0ng.repositories")   //启动jpa  相当于<jpa:repositories
@EnableTransactionManagement    //开启事务
public class SpringDataJPAConfig {

    /**
     * <!--    数据源-->
     *     <bean class="com.alibaba.druid.pool.DruidDataSource" name="dataSource">
     *         <property name="url" value="jdbc:mysql://localhost:3306/test"></property>
     *         <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
     *         <property name="username" value="root"></property>
     *         <property name="password" value="123456"></property>
     *     </bean>
     * @return
     */
    @Bean
    public DataSource dataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        return dataSource;
    }

    /**
     * <!--    entityManagerFactory-->
     *     <bean name="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
     *         <property name="jpaVendorAdapter">
     * <!--            hibernate实现-->
     *             <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
     * <!--                数据库表创建方式，true相当于update，没有是none，false为create模式-->
     *                 <property name="generateDdl" value="true"></property>
     *                 <property name="showSql" value="true"></property>
     *             </bean>
     *         </property>
     *
     * <!--        设置需要扫描的实体类包-->
     *         <property name="packagesToScan" value="com.gzl0ng.pojo"></property>
     *         <property name="dataSource" ref="dataSource"></property>
     *     </bean>
     * @return
     */

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.gzl0ng.pojo");
        factory.setDataSource(dataSource());
        return factory;
    }

    /**
     *  <bean class="org.springframework.orm.jpa.JpaTransactionManager" name="transactionManager">
     *         <property name="entityManagerFactory" ref="entityManagerFactory"></property>
     *     </bean>
     * @param entityManagerFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
