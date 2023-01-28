package com.zj.sys.mapper;

import com.zj.sys.dto.MenuDto;
import com.zj.sys.dto.RoleMenu;
import com.zj.sys.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:22
 */
@Mapper
public interface MenuMapper {
    /**
     * @description: 插入menu菜单
     * @param: Menu
     * @return: int
     * @author: 赵健
     * @date: 2023/1/12 17:26
     */
    int insertMenu(MenuDto menu);
    /**
     * @description: 将菜单id和角色id绑定放入MenuRole表中
     * @return:int
     * @param: [roleMenuList]
     * @author 赵健
     * @date 2023/1/13 20:35
     */
    int bindingMenuToRole(List<RoleMenu> roleMenuList);
    /**
     * @description: 通过角色
     * @return: java.util.List<com.zj.sys.entity.Menu>
     * @param: [roleId]
     * @author 赵健
     * @date 2023/1/13 20:39
     */
    List<Menu> getMenuListByRoleId(String roleId);

    /**
     * @description: 查询menu表
     * @return: java.util.List<com.zj.sys.entity.Menu>
     * @param: [menuDto]
     * @author 赵健
     * @date 2023/1/13 20:40
     */
    List<Menu> selectMenuList(MenuDto menuDto);

    /**
     * @description: 通过角色id删除于菜单的绑定
     * @return: int
     * @param: [roleId]
     * @author 赵健
     * @date 2023/1/13 20:41
     */
    int deleteMenuRoleByRoleId(String roleId);
}
