package com.zj.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.zj.sys.config.BaseController;
import com.zj.sys.config.ControllerUtils;
import com.zj.sys.config.Result;
import com.zj.sys.dto.RoleDto;
import com.zj.sys.dto.TokenUser;
import com.zj.sys.dto.insert.Insert;
import com.zj.sys.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 15:29
 */
@BaseController(value = "/sys/role/")
public class RoleController extends ControllerUtils {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @description: 新建一个角色
     * @return: com.alibaba.fastjson.JSONObject
     * @param: [request, roleDto]
     * @author 赵健
     * @date 2023/1/13 22:04
     */

    @PostMapping(value = "insertRole")
    public JSONObject insertRole(HttpServletRequest request, @RequestBody @Validated(Insert.class) RoleDto roleDto) {
        TokenUser tokenUser = solveToken(request);
        String roleId = tokenUser.getRoleId();
        // TODO 添加验证角色添加的权限
        Result<String> result = roleService.insertRole(roleDto);
        return result.toJSON();
    }

    /**
     * @description: 查询角色列表
     * @return: com.alibaba.fastjson.JSONObject
     * @param: [roleDto]
     * @author 赵健
     * @date 2023/1/13 22:14
     */
    @GetMapping("selectRoles")
    public JSONObject selectRoles(@RequestBody RoleDto roleDto) {
        return roleService.selectRoleList(roleDto).toJSON();
    }
}
