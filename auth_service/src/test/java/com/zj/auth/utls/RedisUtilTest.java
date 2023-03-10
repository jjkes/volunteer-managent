package com.zj.auth.utls;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisUtilTest extends BaseTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSetStringValue() {
        redisUtil.setObject("123","asdfasdf");
    }

    public void testGetStringValue() {
    }

    public void testSetObject() {
    }

    public void testGetObject() {
    }

    public void testSetList() {
    }

    public void testGetList() {
    }

    public void testRemoveKeys() {
    }

    public void testClearKeys() {
    }
}