package com.wxw.boot.service;

import com.wxw.boot.domain.User;

import javax.transaction.Transactional;

/**
 * @ Author ：wxw.
 * @ Date ： 13:31 2020/7/10
 * @ Description：业务层
 * @ Version:   v_0.0.1
 */
public interface UserService {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);


    User update(String name, String password);

}
