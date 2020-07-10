package com.wxw.boot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;

/**
 * @ Author ：wxw.
 * @ Date ： 13:05 2020/7/10
 * @ Description：用户类
 * @ 该注解声明一个实体类，与数据库中的表对应
 * @ Version:   v_0.0.1
 */
@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Max(50)
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}