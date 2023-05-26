package com.zj.sys.service;

import com.zj.sys.config.Result;
import com.zj.entities.sys.dto.MenuDto;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.entity.Menu;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:29
 */

public interface MenuService {

    /**
     * @param menuDto menu表的传输实体
     * @return com.zj.entity.Result<java.lang.Object>
     * @description: 插入一条菜单信息
     * @author 赵健
     * @date 2023/1/13 20:42
     */
    Result<Object> insertMenu(MenuDto menuDto);

    /**
     * @description: 获取当前登录用户的菜单列表
     * @return: com.zj.entity.Result
     * @param: [tokenUser]
     * @author 赵健
     * @date 2023/1/13 20:43
     */
    Result<List<Menu>> getUserMenuList(TokenUser tokenUser);

    /**
     * @description: 通过将角色于菜单绑定
     * @return: com.zj.entity.Result
     * @param: [roleId, menuIds]
     * @author 赵健
     * @date 2023/1/13 20:43
     */
    Result bingingMenuToRole(String roleId, String menuIds);

    /**
     * @description: 查询角色列表
     * @return: com.zj.entity.Result<java.util.List < com.zj.sys.entity.Menu>>
     * @param: [menuDto]
     * @author 赵健
     * @date 2023/1/13 20:44
     */
    Result<List<Menu>> selectMenuList(MenuDto menuDto);
}
