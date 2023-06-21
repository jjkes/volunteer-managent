package com.zj.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author 赵健
 * @version 1.0
 * @description: BASE64加解码
 * @date 2023/1/25 11:13
 */

public class Base64Util {




    /**
     * @description: 将字符串编码为base64
     * @return  java.lang.String
     * @param  str 字符串
     * @author 赵健
     * @date 2023/1/25 15:25
     */
    public static String encodeBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @description: 将base64字符串解码
     * @return java.lang.String
     * @param str 字符串
     * @author 赵健
     * @date 2023/1/25 15:26
     */
    public static String decodeBase64(String str) {
        byte[] decode = Base64.getDecoder().decode(str);
        return new String(decode, StandardCharsets.UTF_8);
    }

    /**
     * @description: 将byte[]类型的数据编码为base64字符串
     * @return java.lang.String
     * @param bytes byte
     * @author 赵健
     * @date 2023/1/25 15:22
     */
    public static String encodingByte(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * @description: 将base64字符串解码为byte[]类型
     * @return byte[]
     * @param str 字符串
     * @author 赵健
     * @date 2023/1/25 15:23
     */
    public static byte[] decodeToByte(String str) {
        return Base64.getDecoder().decode(str);
    }

}
