package com.zj.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2022/11/6 11:15
 */

public class PwdUtil {
    public static String toMD5(String password){
        return DigestUtils.md5Hex(password.getBytes());
    }

    public static void main(String[] args) {
        String s = PwdUtil.toMD5("123123");
        System.err.println(s);
    }
}
