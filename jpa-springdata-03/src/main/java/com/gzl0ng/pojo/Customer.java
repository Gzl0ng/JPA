package com.gzl0ng.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */

@Data
@Entity           //作为hibernate 实体类
@Table(name = "tb_customer")    //映射的表名
public class Customer {

    /*
    *
    * */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主键自增类型
    @Column(name = "id")  //数据表字段实际名字
    private Long custId;//客户主键

    @Column(name = "cust_name")
    private String custName;//客户名称

    @Column(name = "cust_address")
    private String custAddress;//客户地址


    /**
     * 	PERSIST	只有插入执行插入操作
     * 	ALL	所有持久化操作
     * 	MERGE	只有修改才会执行关联操作
     * 	REMOVE	只有删除才会执行关联操作
     *
     * 	    fetch 设置是否懒加载
     * 	        EAGER 立即加载（默认）
     * 	        LAZY 懒加载（直到用到对象才会进行查询,因为不是所有的关联对象都需要用到）
     *
     * 	    orphanRemoval 关联移除（通常在修改的时候会用到）
     * 	        一旦把关联的数据设置null，或者修改为其他的关联数据，如果想删除关联数据，就可以设置true
     * 	    optional    现在关联的对象不能为null
     * 	        true 可以为null（默认）
     */
    //单向关联 一对一
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,optional = false)  //设置外表操作跟随方式，第二个设置为外表属性的加载方式，第三个设置更新主表时外键设null时删除对应外表数据,第四个设置是否可以为空
    @JoinColumn(name = "account_id")  //为外键起名
    private Account account;
}
