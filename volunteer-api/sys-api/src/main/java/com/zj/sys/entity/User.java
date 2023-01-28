package com.zj.sys.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
* @discription user实体类
* @author 赵健
* @date 2023/1/12 17:37
* @version 1.0
*/
@Data
public class User {

    private String id;
    private String name;
    private String alias;
    private String account;
    private String password;
    private String email;
    private Integer sex;
    private String tel;
    private String roleId;
    private String schoolId;
    private LocalDateTime updatePasswordTime;
    private Integer passwordErrorTimes;

    public void generateId() {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
    }
}
