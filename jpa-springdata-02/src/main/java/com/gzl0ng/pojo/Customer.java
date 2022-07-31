package com.gzl0ng.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 郭正龙
 * @date 2022-07-31
 */

@Data
@Entity           //作为hibernate 实体类
@Table(name = "customer")    //映射的表名
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
}
