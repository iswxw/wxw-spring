package com.wxw.controller;

import com.wxw.common.response.Result;
import com.wxw.common.helper.ResultUtil;
import com.wxw.common.request.Team1Dto;
import com.wxw.common.request.User1Dto;
import com.wxw.common.request.User2Dto;
import com.wxw.interfaces.Group1;
import com.wxw.interfaces.Group2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

/**
 * @author weixiaowei
 * @desc: 参数校验
 * @link: https://blog.csdn.net/weixin_44296862/article/details/110913904
 * @link: https://blog.csdn.net/qq_32352777/article/details/108424932
 * @date: 2021/6/19
 */
@RestController
public class CheckController {

    @PostMapping("/insert")
    public Result validatedDemo1(@Validated @RequestBody User1Dto user1Dto){
        return ResultUtil.success(user1Dto);
    }

    @PostMapping("/insert2")
    public Result validatedDemo2(@Validated @RequestBody Team1Dto team1Dto){
        return ResultUtil.success(team1Dto);
    }

    @PostMapping("/insert3")
    public Result validatedDemo3(@Validated(Default.class) @RequestBody User2Dto user2Dto){
        return ResultUtil.success(user2Dto);
    }

    @PostMapping("/insert4")
    public Result validatedDemo4(@Validated(Group1.class) @RequestBody User2Dto user2Dto){
        return ResultUtil.success(user2Dto);
    }

    @PostMapping("/insert5")
    public Result validatedDemo5(@Validated(Group2.class) @RequestBody User2Dto user2Dto){
        return ResultUtil.success(user2Dto);
    }
}
