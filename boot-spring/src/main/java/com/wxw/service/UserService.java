package com.wxw.service;

/**
 * @author Administrator
 */
public class UserService {

    private GoodsService goodsService;

    // 修正:增加 setter 方法
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
}