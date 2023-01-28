package com.zj.sys.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author 赵健
 * @version 1.0
 * @description: BASE64加解码
 * @date 2023/1/25 11:13
 */

public class Base64Util {


    /**  java Util **/


    /**
     * @description: 将字符串编码为base64
     * @return: java.lang.String
     * @param: [str]
     * @author 赵健
     * @date 2023/1/25 15:25
     */
    public static String encodeBase64(String str){
        String s = null;
        try {
            s = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return s;
    }

    /**
     * @description: 将base64字符串解码
     * @return: java.lang.String
     * @param: [str]
     * @author 赵健
     * @date 2023/1/25 15:26
     */
    public static String decodeBase64(String str){
        byte[] decode = Base64.getDecoder().decode(str);
        try {
            return new String(decode,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: 将byte[]类型的数据编码为base64字符串
     * @return: java.lang.String
     * @param: [bytes]
     * @author 赵健
     * @date 2023/1/25 15:22
     */
    public static String encodingByte(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * @description: 将base64字符串解码为byte[]类型
     * @return: byte[]
     * @param: [str]
     * @author 赵健
     * @date 2023/1/25 15:23
     */
    public static byte[] decodeToByte(String str){
        return Base64.getDecoder().decode(str);
    }

}
