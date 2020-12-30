package com.wxw.domain;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ Author ：wxw.
 * @ Date ： 14:41 2020/9/9
 * @ Description：你好
 * @ Version:   v_0.0.1
 */
@Data
public class Person {

    @NotBlank(message = "姓名不能为空")
    private String name;

    /** 年龄 */
    @NotNull(message="年龄不能为空")
    @Max(message="年龄不能超过120岁", value = 120)
    @Min(message="年龄不能小于1岁", value = 1)
    private Integer age;

    private Integer sex;
}
