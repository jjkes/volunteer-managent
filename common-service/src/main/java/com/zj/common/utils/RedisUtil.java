package com.zj.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/5 11:04
 */
@Component
public class RedisUtil {
    private final JedisPool jedisPool;

    /**
    * 设置默认过期时间：10秒
    */
    private final long EXPIRE=10*20;

    @Autowired
    public RedisUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    public void setStringValue(String key, String value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            SetParams setParams = new SetParams();
            setParams.ex(EXPIRE);
            jedis.set(key,value,setParams);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }


    public String getStringValue(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return null;
    }

    /**
     * @description: 这是object类型数据
     * @return: void
     * @param: [key, value]
     * @author 赵健
     * @date 2023/2/13 13:37
     */
    public String setObject(String key, Object value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = SerializeUtil.serializeObject(value);
            if(bytes != null){
                SetParams setParams = new SetParams();
                //设置过期时间
                setParams.ex(EXPIRE);
                return jedis.set(key.getBytes(),bytes,setParams);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return null;
    }

    /**
     * @description: 获取Object类型数据
     * @return: java.lang.Object
     * @param: [key]
     * @author 赵健
     * @date 2023/2/13 13:37
     */
    public Object getObject(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = jedis.get(key.getBytes());
            Object o = SerializeUtil.unSerializeObject(bytes);
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return null;
    }


    /**
     * @description: 存放列表数据
     * @return: boolean
     * @param: [key, list]
     * @author 赵健
     * @date 2023/2/13 13:37
     */
    public boolean setList(String key, List<Object> list){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = SerializeUtil.serializeList(list);
            if(bytes != null){
                SetParams setParams = new SetParams();
                setParams.ex(EXPIRE);
               return jedis.set(key.getBytes(), bytes, setParams).equalsIgnoreCase("OK");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return false;
    }

    /**
     * @description: 获取列表
     * @return: java.util.List<java.lang.Object>
     * @param: [key]
     * @author 赵健
     * @date 2023/2/13 13:36
     */
    public List<Object> getList(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = jedis.get(key.getBytes());
            return SerializeUtil.unSerializeList(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return null;
    }


    public String removeKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sentinelRemove(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public void clearKeys() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.functionFlush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
    public static void main(String[] args) {
//        setStringValue("123","123");
//        System.err.println(jedis.get("123"));
    }
}


