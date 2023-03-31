package com.zj.common.constant;

/**
 * @description: TODO
 * @author 赵健
 * @date 2023/1/17 16:37
 * @version 1.0
 */
public enum CommonEnum {
    TRUE("true"),
    FALSE("false"),
    TOKEN_STR("AuthorizationToken"),
    // redis中该用户信息的key
    REDIS_SESSION_ID("sessionRedisId"),
    ;
    private String str;
    CommonEnum(String str){
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
