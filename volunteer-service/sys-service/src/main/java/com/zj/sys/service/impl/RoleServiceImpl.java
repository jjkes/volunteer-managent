package com.zj.sys.service.impl;

import com.zj.enums.StateEnum;
import com.zj.sys.config.Result;
import com.zj.sys.dto.RoleDto;
import com.zj.sys.entity.Role;
import com.zj.sys.mapper.RoleMapper;
import com.zj.sys.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:54
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Result<String> insertRole(RoleDto roleDto) {
        Result result = new Result<>();
        roleDto.setId(UUID.randomUUID().toString());
        int i = roleMapper.insertRole(roleDto);
        if (i > 0) {
            result.setResultEnum(StateEnum.SUCCESS);
        } else {
            result.setResultEnum(StateEnum.FAILED);
        }
        return result;
    }

    @Override
    public Result<List<Role>> selectRoleList(RoleDto roleDto) {
        Result<List<Role>> result = new Result<>();
        List<Role> roles = roleMapper.selectRoleList(roleDto);
        result.setResultEnum(StateEnum.SUCCESS);
        result.setData(roles);
        return result;
    }


}
