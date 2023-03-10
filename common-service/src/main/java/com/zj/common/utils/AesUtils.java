package com.zj.common.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/25 11:35
 */

public class AesUtils {
    /** 算法/模式/补码方式 */
    private static final String transformation="AES/CBC/PKCS5Padding";
    /**
     * 密钥（16位）
     */
    private static String key = "123123123123123123";
    /**
    * 偏移量（16位）
    */
    private static String iv = "1234567890123456";

    private static SecretKeySpec secretKey=null;

    static {
        try {
            if(key.length()<16){
                throw new Exception("key 的长度小于16");
            }
            secretKey = new SecretKeySpec(key.substring(0,16).getBytes("UTF-8"),"AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEncodeStr(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,new IvParameterSpec(iv.getBytes("UTF-8")) );
        byte[] result = cipher.doFinal(str.getBytes("UTF-8"));
        String s = Base64Util.encodingByte(result);
        return s;
    }

    public static String encrypt(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE,secretKey,new IvParameterSpec(iv.getBytes("UTF-8")));

        String s= new String(cipher.doFinal(Base64Util.decodeToByte(str)),"UTF-8");
        return s;
    }

    public static void main(String[] args) {
        try {
            String s = getEncodeStr("123123");
            System.err.println(s);
            String as = encrypt(s);
            System.err.println(as);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
