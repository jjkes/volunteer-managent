package com.zj.entities.sys.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 11:27
 */
@Data
@Alias("Role")
public class Role {

    private String id;

    private String roleName;

    private String belongId;

    private String introduce;
}
