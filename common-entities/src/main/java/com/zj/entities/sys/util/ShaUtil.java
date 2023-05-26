package com.zj.entities.sys.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 赵健
 * @version 1.0
 * @description: 散列加密
 * @date 2023/1/25 15:32
 */

public class ShaUtil {
    /**
    * 散列加密算法 SHA3-512
    */
    private final static String algorithm="SHA-512";


    public static String decrypt(String str){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes("UTf-8"));
        } catch (NoSuchAlgorithmException|UnsupportedEncodingException e ) {
            e.printStackTrace();
        }
        byte[] digest = messageDigest.digest();
        String s = Base64Util.encodingByte(digest);
        return s;
    }

    public static void main(String[] args) {
        System.err.println(decrypt("123123"));
    }
}
