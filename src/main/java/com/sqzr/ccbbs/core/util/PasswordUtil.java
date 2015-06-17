package com.sqzr.ccbbs.core.util;

import com.xiaoleilu.hutool.RandomUtil;
import com.xiaoleilu.hutool.SecureUtil;

/**
 * 密码相关工具类
 * User: WeiYang
 * Date: 2015/6/2
 * Time: 15:56
 */
public class PasswordUtil {
    /**
     * 密码加密
     * 算法 -> md5(md5(password + salt) + salt)
     *
     * @param password 密码
     * @param salt     盐
     * @return 加密后的密码
     */
    public static String encrypt(String password, String salt) {
        return SecureUtil.md5(SecureUtil.md5(password + salt, "utf-8") + salt, "utf-8");
    }

    /**
     * 随机生成salt 默认为五位数
     *
     * @return salt
     */
    public static String genrateSalt() {
        return RandomUtil.randomString(5);
    }

}
