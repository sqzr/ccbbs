package com.sqzr.ccbbs.core.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期工具类
 * 日期工具类的补充
 *
 * @author weiyang
 */
public class DateUtil {
    /**
     * 默认日期格式
     */
    private static final String[] parsePatterns = new String[]{
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
            "yyyy-MM-dd'T'HH:mm",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd"
            // 这里可以增加更多的日期格式，用得多的放在前面
    };

    /**
     * 使用默认的日期格式将字符串转换为日期
     *
     * @param str 要转换的字符串
     * @return 转换后的日期
     * @throws ParseException 没有匹配的日期格式
     */
    public static Date parseDate(String str) throws ParseException {
        return DateUtils.parseDate(str, parsePatterns);
    }

    /**
     * 使用给定的日期格式将字符串转换为日期
     *
     * @param str          要转换的字符串
     * @param parsePattern 日期格式字符串
     * @return 转换后的日期
     * @throws ParseException 日期格式不匹配
     */
    public static Date parseDate(String str, String parsePattern) throws ParseException {
        return DateUtils.parseDate(str, new String[]{parsePattern});
    }
}
