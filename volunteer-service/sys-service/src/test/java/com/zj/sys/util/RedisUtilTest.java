package com.zj.sys.util;

import com.zj.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;
    @Test
    void setStringValue() {
        redisUtil.setStringValue("123","123");
        String stringValue = redisUtil.getStringValue("123");
        System.err.println("key:123,value:"+stringValue);
        String tempValue = redisUtil.getStringValue("123123");
        System.err.println("key:123123,value:"+tempValue);

    }

    @Test
    void setObject() {
        String s = new String();
        s="s";
        redisUtil.setObject("123",s);
    }

    @Test
    void getObject(){
        Object object = redisUtil.getObject("123");
        if(object instanceof String){
            System.err.println((String)object);
        }else{
            System.err.println("错误");
        }
    }
}