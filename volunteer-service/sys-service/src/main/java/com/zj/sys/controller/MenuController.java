package com.zj.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.zj.common.constant.StateEnum;
import com.zj.sys.config.BaseController;
import com.zj.sys.config.ControllerUtils;
import com.zj.sys.config.Result;
import com.zj.entities.sys.dto.MenuDto;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.dto.insert.InsertMenu;
import com.zj.sys.service.MenuService;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:28
 */
@BaseController("/sys/menu/")
public class MenuController extends ControllerUtils {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * @description: 新建一个菜单
     * @return: com.alibaba.fastjson.JSONObject
     * @param: [menu]
     * @author 赵健
     * @date 2023/1/13 20:20
     */
    @PostMapping("insertMenu")
    public JSONObject insertMenu(@RequestBody @Validated(InsertMenu.class) MenuDto menu) {
        Result<Object> result = menuService.insertMenu(menu);
        return result.toJSON();
    }

    /**
     * @description: 获取当前登录的用户菜单列表
     * @return: com.alibaba.fastjson.JSONObject
     * @param: [request]
     * @author 赵健
     * @date 2023/1/13 20:20
     */

    @GetMapping("getUserMenu")
    public JSONObject getUserMenu(HttpServletRequest request) {
        TokenUser tokenUser = solveToken(request);
        return menuService.getUserMenuList(tokenUser).toJSON();
    }

    /**
     * @description: 查询菜单那列表 TODO 未分页
     * @return: com.alibaba.fastjson.JSONObject
     * @param: [menuDto]
     * @author 赵健
     * @date 2023/1/13 20:21
     */
    @GetMapping("selectMenuList")
    public JSONObject selectMenuList(@RequestBody MenuDto menuDto) {
        return menuService.selectMenuList(menuDto).toJSON();
    }

    /**
     * @description: 将角色id和菜单id批量绑定
     * @return: com.alibaba.fastjson.JSONObject
     * @param: [request, roleId, menuIds]
     * @author 赵健
     * @date 2023/1/13 20:22
     */
    @PostMapping("bingingMenuToRole")
    public JSONObject bingingMenuToRole(HttpServletRequest request, @RequestParam("roleId") String roleId, @RequestParam("menuIds") String menuIds) {
        if (StringUtils.isEmpty(roleId) || StringUtils.isEmpty(menuIds)) {
            return new Result<>().setResultEnum(StateEnum.FROM_FAIL).toJSON();
        }
        TokenUser tokenUser = solveToken(request);
        String tokenUserRoleId = tokenUser.getRoleId();
        // TODO 验证当前用户的权限
        return menuService.bingingMenuToRole(roleId, menuIds).toJSON();
    }

}
