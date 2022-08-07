package com.gzl0ng.pojo;

import lombok.*;

import javax.persistence.*;

/**
 * @author 郭正龙
 * @date 2022-08-06
 */
@Getter
@Setter
@ToString
@Entity           //作为hibernate 实体类
@Table(name = "tb_message")    //映射的表名
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String info;

    public Message(String info){
        this.info=info;
    }

    public Message(String info,Customer customer){
        this.info =info;
        this.customer = customer;
    }

    public Message(){

    }

    //多对一
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", customerId=" + customer.getCustId() +
                ", customerName=" + customer.getCustName() +
                '}';
    }
}
