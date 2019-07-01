package com.baiyu.tmall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {
    /**
     * 获取当前时间字符串
     * @return
     */
    public static String timeFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return sdf.format(date);
    }
}
