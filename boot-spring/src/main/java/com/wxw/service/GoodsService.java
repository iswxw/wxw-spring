package com.wxw.service;


public class GoodsService {
    private UserService userService;

    // 修正:增加 setter 方法
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}