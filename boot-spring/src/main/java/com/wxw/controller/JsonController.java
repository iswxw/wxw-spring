package com.wxw.controller;

import com.wxw.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: wxw
 * @date: 2021-04-15-0:43
 * @link:
 * @description:
 */
@RestController
public class JsonController {

    @GetMapping("/api/getUser")
    public Object getUser() {
        List<User> users = new ArrayList<User>();
        users.add(new User("1", "小京", "北京"));
        users.add(new User("2", "小海", "上海"));
        users.add(new User("1", "小广", null));
        return users;
    }

}
