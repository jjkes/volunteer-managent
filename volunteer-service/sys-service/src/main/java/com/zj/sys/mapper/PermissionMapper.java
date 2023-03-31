package com.zj.sys.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/12 14:25
 */
@Mapper
public interface PermissionMapper {
    int savePermission();
}
