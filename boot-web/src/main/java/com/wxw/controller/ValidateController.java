package com.wxw.controller;

import com.wxw.domain.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author ：wxw.
 * @ Date ： 11:17 2020/9/9
 * @ Description：参数校验接口
 * @ Version:   v_0.0.1
 */
@Validated
@RestController
public class ValidateController {

    @PostMapping("/test3")
    public Person testData3(@RequestBody @Valid Person person){
        return person;
    }

    @GetMapping("/test2")
    public Map testData2(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "age") @NotBlank(message = "你好") Integer age,
            @RequestParam @NotBlank(message = "你好") Integer sex) {
        Map map = new HashMap();
        map.put("all", "我若成佛，天下五魔；我若成魔，佛奈我何！");
        map.put("name", name);
        map.put("age", age);
        return map;
    }

    @GetMapping("/test1")
    public String testData1(@Valid @RequestParam(value="name",required = false) @NotNull(message = "你好") String name){
         return "我若成佛，天下五魔；我若成魔，佛奈我何！";
    }

}
