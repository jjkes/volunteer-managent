package com.zj.entities.sys.dto;

import lombok.Data;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 14:02
 */
@Data
public class RoleMenu {
    private String id;
    private String menuId;
    private String roleId;

    public RoleMenu(String id, String menuId, String roleId) {
        this.id=id;
        this.menuId=menuId;
        this.roleId= roleId;
    }
}
