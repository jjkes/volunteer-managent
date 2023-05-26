package com.zj.entities.file.entity;

import lombok.Data;

/**
 * @author 赵健
 * @version 1.0
 * @description: 权限实体类
 * @date 2023/3/12 14:32
 */
@Data
public class PermissionEntity {
    private Integer id;
    private String name;
    private String path;
}
