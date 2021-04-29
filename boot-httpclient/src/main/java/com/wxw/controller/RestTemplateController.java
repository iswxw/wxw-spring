package com.wxw.controller;

import com.wxw.common.utils.DataHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author: wxw
 * @date: 2021-04-17-0:47
 * @link:
 * @description:
 */
@RestController
public class RestTemplateController {


    @GetMapping(value = "/getList")
    public List<String> getList(){
        return DataHelper.date();
    }

}
