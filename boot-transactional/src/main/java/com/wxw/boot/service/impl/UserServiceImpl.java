package com.wxw.boot.service.impl;

import com.wxw.boot.domain.User;
import com.wxw.boot.repository.UserRepository;
import com.wxw.boot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 14:07 2020/7/10
 * @ Description：业务实现层
 * @ Version:   v_0.0.1
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByNameAndAge(String name, Integer age) {
        return userRepository.findByNameAndAge(name,age);
    }

    @Override
    public User update(String name, String password) {
        return userRepository.findUser(name);
    }
}
