package com.zj.sys.mapper;

import com.zj.entities.sys.dto.RoleDto;
import com.zj.entities.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:52
 */
@Mapper
public interface RoleMapper {
    int insertRole(RoleDto roleDto);

    List<Role> selectRoleList(RoleDto roleDto);

    Role selectRoleById(@Param("id") String id);
}
