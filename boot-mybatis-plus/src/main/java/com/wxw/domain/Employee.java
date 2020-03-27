package com.wxw.domain;

import lombok.Data;

/**
 * @Author: wxw
 * @create: 2020-03-27-12:23
 */
@Data
public class Employee {

    private Long id;
    private String lastName;
    private String email;
    private String gender;
    private Long d_id;

    public Employee(Long id, String lastName, String email, String gender) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

}
