package com.zj.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class redisDaoTest {


    @Autowired
    private RedisDao redisDao;
    @Test
    void set() {
        redisDao.set("key","val");
    }
    @Test
    void get() {
        System.err.println(redisDao.get("key"));
    }
}