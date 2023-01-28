package com.zj.enums;

import lombok.Data;

/**
 * @description: TODO
 * @author 赵健
 * @date 2023/1/17 16:37
 * @version 1.0
 */
public enum CommonEnum {
    TRUE("true"),
    FALSE("false"),
    ;
    private String str;
    private CommonEnum(String str){
        this.str = str;
    }
}
