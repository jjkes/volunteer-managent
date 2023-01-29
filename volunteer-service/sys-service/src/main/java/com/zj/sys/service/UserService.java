package com.zj.sys.service;

import com.zj.sys.config.Result;
import com.zj.sys.dto.UserDto;
import com.zj.sys.entity.LoginUser;

/**
 * @author a1204
 * @version 1.0
 * @description: TODO
 * @date 2022/9/25 22:44
 */

public interface UserService {
    /***
     * @description 用户登录，身份校验成功，返回值Result的data中返回token
     * @return com.zj.entity.Result<java.lang.String>
     * @param loginUser 登录实体类
     * @author 赵健
     * @date 2022/9/25 22:46
     */
    Result<String> clientLogin(LoginUser loginUser);

    Result updateUser(UserDto userDto);
}
