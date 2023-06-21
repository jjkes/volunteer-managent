package com.zj.entities.sys.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author 赵健
 * @version 1.0
 * @description: 菜单实体类
 * @date 2023/1/12 17:30
 */
@Data
@Alias("Menu")
public class Menu {
    /** 菜单id */
    private String id;
    /** 菜单名 */
    private String menuName;
    /** vue路由地址 */
    private String routerPath;
    /** 菜单排序 */
    private String sort;
    /** 菜单介绍 */
    private String introduce;
}