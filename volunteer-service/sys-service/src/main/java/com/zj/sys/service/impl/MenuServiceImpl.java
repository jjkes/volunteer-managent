package com.zj.sys.service.impl;

import com.zj.common.constant.StateEnum;
import com.zj.sys.config.Result;
import com.zj.entities.sys.dto.MenuDto;
import com.zj.entities.sys.dto.RoleMenu;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.entity.Menu;
import com.zj.sys.mapper.MenuMapper;
import com.zj.sys.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:29
 */
@Service
public class MenuServiceImpl implements MenuService {
    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    /**
     * @description: 插入一条菜单信息
     * @return: com.zj.entity.Result<java.lang.Object>
     * @param: [menu]
     * @author 赵健
     * @date 2023/1/13 20:42
     */
    @Override
    public Result<Object> insertMenu(MenuDto menuDto) {
        Result result = new Result<>();
        menuDto.setId(UUID.randomUUID().toString());
        int i = menuMapper.insertMenu(menuDto);
        if (i > 0) {
            result.setResultEnum(StateEnum.SUCCESS);
        } else {
            result.setResultEnum(StateEnum.FAILED);
        }
        return result;
    }

    /**
     * @description: 获取当前登录用户的菜单列表
     * @return: com.zj.entity.Result
     * @param: [tokenUser]
     * @author 赵健
     * @date 2023/1/13 20:43
     */
    @Override
    public Result getUserMenuList(TokenUser tokenUser) {
        Result<List<Menu>> result = new Result<>();
        String roleId = tokenUser.getRoleId();
        List<Menu> menuList = menuMapper.getMenuListByRoleId(roleId);
        if (!ObjectUtils.isEmpty(menuList)) {
            // TODO 写出递归结构的菜单列表，写一个vo承载这些结构
            result.setResultEnum(StateEnum.SUCCESS);
            result.setData(menuList);
        } else {
            result.setResultEnum(StateEnum.FAILED);
        }
        return result;
    }

    /**
     * @description: 通过将角色于菜单绑定
     * @return: com.zj.entity.Result
     * @param: [roleId, menuIds]
     * @author 赵健
     * @date 2023/1/13 20:43
     */
    @Override
    public Result bingingMenuToRole(String roleId, String menuIds) {
        Result result = new Result<>();
        // 删除之前和该角色绑定的条目
        menuMapper.deleteMenuRoleByRoleId(roleId);
        String[] menuIdArr = menuIds.split(",");
        List<RoleMenu> menuIdList = new ArrayList<>();
        for (String menuId : menuIdArr) {
            menuIdList.add(new RoleMenu(UUID.randomUUID().toString(), menuId, roleId));
        }
        int i = menuMapper.bindingMenuToRole(menuIdList);
        if (i > 0) {
            result.setResultEnum(StateEnum.SUCCESS);
        } else {
            result.setResultEnum(StateEnum.FAILED);
        }
        return result;
    }

    /**
     * @description: 查询角色列表
     * @return: com.zj.entity.Result<java.util.List < com.zj.sys.entity.Menu>>
     * @param: [menuDto]
     * @author 赵健
     * @date 2023/1/13 20:44
     */
    @Override
    public Result<List<Menu>> selectMenuList(MenuDto menuDto) {
        Result<List<Menu>> result = new Result<>();
        List<Menu> menuList = menuMapper.selectMenuList(menuDto);
        result.setData(menuList);
        result.setResultEnum(StateEnum.SUCCESS);
        return result;
    }
}
