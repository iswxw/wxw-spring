package com.wxw.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxw
 * @date: 2021-04-17-1:23
 * @link:
 * @description:
 */
public class DataHelper {

    public static List<String> date(){
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stringList.add("wxw"+i);
        }
        return stringList;
    }
}
