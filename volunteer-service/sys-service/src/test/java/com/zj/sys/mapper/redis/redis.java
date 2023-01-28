package com.zj.sys.mapper.redis;



import com.zj.sys.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2022/11/7 21:08
 */
@SpringBootTest
public class redis {
    public final RedisUtils redisUtils;

    public redis(RedisUtils redisUtils){
        this.redisUtils = redisUtils;
    }
    @Test
    public void test() {
        redisUtils.set("key", "key");
//        String s = redisUtils.get("key");
//        System.err.println(s);
    }
}
