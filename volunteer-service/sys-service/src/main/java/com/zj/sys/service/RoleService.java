package com.zj.sys.service;

import com.zj.sys.config.Result;
import com.zj.sys.dto.RoleDto;
import com.zj.sys.entity.Role;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:53
 */

public interface RoleService {

    Result<String> insertRole(RoleDto roleDto);

    Result<List<Role>> selectRoleList(RoleDto roleDto);
}
