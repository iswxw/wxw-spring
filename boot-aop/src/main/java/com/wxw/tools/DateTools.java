package com.wxw.tools;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @ Author ：wxw.
 * @ Date ： 16:19 2020/6/5
 * @ Description：日期处理工具
 * @ Version:   v_0.0.1
 */
public class DateTools {

    /**
     * 格式化 时间戳---->yyyy-MM-dd HH:mm:ss
     */
    public static String getLong2YyyyMmDdHhMmSs(long time) {
        return DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss");
    }

}
