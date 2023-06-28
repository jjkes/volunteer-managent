package com.zj.common.constant;


public enum StateEnum {
    SUCCESS(0,"成功"),
    FAILED(1,"失败"),
    FROM_FAIL(2,"表单验证失败"),

    AUTHORITY_FAIL(3,"您无法访问此功能，因为没有相关权限"),
    FILE_SAVE_ERROR(200,"文件保存失败！"),

    /**** sys_service系统错误*****/
    // 登录相关
    LOGIN_FAILED(101,"账号或密码错误"),
    LOGIN_PARAMS(102,"账号或密码为空"),
    LOGIN_EXPIRES(103,"登录过期"),
    VERIFY_RANDOM_STR_IS_NULL(103,"验证随机码为空"),
    VERIFY_RANDOM_ERROR(114,"验证码错误"),



    ;



    StateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
