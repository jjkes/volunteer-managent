package com.zj.sys.mapper;

import com.zj.entities.sys.dto.UserDto;
import com.zj.entities.sys.entity.LoginUser;
import com.zj.entities.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author a1204
 * @version 1.0
 * @description: TODO
 * @date 2022/9/25 22:53
 */
@Mapper
public interface UserMapper {
    User getUserById(String id);

    int updateUser(UserDto user);

    int insertUser(User user);

    User getLoginUser(LoginUser loginUser);

    User getUserByUsername(String username);
}
