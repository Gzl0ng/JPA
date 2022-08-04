package com.gzl0ng.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 郭正龙
 * @date 2022-08-04
 */
@Entity
@Table(name = "tb_account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
}
