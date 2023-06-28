package com.zj.common.enums;


/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/6/27 9:58
 */
public enum AuthEnum {
    后台系统访问权限("123","HTXTFWQX","后台系统访问权限"),
    定时任务查看权限("2","DSRWCKQX","定时任务查看权限"),
    定时任务管理权限("3","DSRWGLQX","定时任务管理权限"),
    ;
    private String id;
    private String authStr;


    private String description;


    AuthEnum(String id, String authStr, String description) {
        this.id = id;
        this.authStr = authStr;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthStr() {
        return authStr;
    }

    public void setAuthStr(String authStr) {
        this.authStr = authStr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
