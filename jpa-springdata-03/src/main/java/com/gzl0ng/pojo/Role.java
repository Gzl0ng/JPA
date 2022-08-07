package com.gzl0ng.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 郭正龙
 * @date 2022-08-06
 */
@Data
@Entity           //作为hibernate 实体类
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String rName;

public Role(String rName){
    this.rName=rName;
}

    public Role(Long id, String rName) {
        this.id = id;
        this.rName = rName;
    }

    public Role(){

}
}
