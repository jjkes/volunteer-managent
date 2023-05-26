package com.zj.entities.sys.dto;


import com.zj.entities.sys.dto.update.UpdateMenu;
import com.zj.entities.sys.dto.insert.InsertMenu;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 11:50
 */
@Data
public class MenuDto {
    @NotEmpty(message = "菜单id不能为空",groups = UpdateMenu.class)
    private String id;
    /** 菜单名 */
    @NotEmpty(message = "菜单名不能为空",groups = InsertMenu.class)
    private String menuName;
    /** vue路由地址 */
    @NotEmpty(message = "路由地址不能为空",groups = InsertMenu.class)
    private String routerPath;
    /** 菜单排序 */
    @NotEmpty(message = "菜单排序不能为空",groups = InsertMenu.class)
    private String sort;
    /** 菜单介绍 */
    @NotEmpty(message = "介绍信息不能为空",groups = InsertMenu.class)
    private String introduce;
}
