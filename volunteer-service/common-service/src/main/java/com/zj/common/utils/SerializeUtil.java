package com.zj.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: 序列化Object工具类
 * @date 2023/2/4 13:50
 */

public class SerializeUtil {


    /**
     * @description: 序列化Object类
     * @return: byte[]
     * @param: [object]
     * @author 赵健
     * @date 2023/2/4 14:00
     */
    public static byte[] serializeObject(Object object) {
        if (object == null) {
            throw new NullPointerException("can not serialize null");
        }
        try (ByteArrayOutputStream baps = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baps);
        ) {
            oos.writeObject(object);
            baps.flush();
            return baps.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: 反序列化byte[]为Object
     * @return: java.lang.Object
     * @param: [bytes]
     * @author 赵健
     * @date 2023/2/4 14:08
     */
    public static Object unSerializeObject(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("can not unSerialize null byte Array");
        }
        try (
                ByteArrayInputStream bas = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bas);
        ) {
            Object o = ois.readObject();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add("asdfa");

        List<Object> objects = unSerializeList(serializeList(list));
        System.err.println(objects.toString());
    }

    /**
     * @description: 序列化list
     * @return: byte[]
     * @param: [list]
     * @author 赵健
     * @date 2023/2/4 14:13
     */

    public static byte[] serializeList(List<Object> list) {
        if (list == null) {
            throw new NullPointerException("can not serialize null");
        }
        try (ByteArrayOutputStream baps = new ByteArrayOutputStream();
             ObjectOutputStream ops = new ObjectOutputStream(baps);
        ) {
            for (Object object : list) {
                ops.writeObject(object);
            }
            ops.writeObject(null);
            baps.flush();
            return baps.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @description: 反序列化byte[]为list
     * @return: java.util.List<java.lang.Object>
     * @param: [bytes]
     * @author 赵健
     * @date 2023/2/4 14:24
     */

    public static List<Object> unSerializeList(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("can not unSerialize null byte array");
        }
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
        ) {
            List<Object> list = new ArrayList<Object>();
            Object object = null;
            while ((object = ois.readObject()) != null) {
                list.add(object);
            }
            return list;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
