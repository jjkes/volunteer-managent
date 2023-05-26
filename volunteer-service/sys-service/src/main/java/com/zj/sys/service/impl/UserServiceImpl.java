package com.zj.sys.service.impl;

import com.zj.common.constant.StateEnum;
import com.zj.sys.config.Result;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.dto.UserDto;
import com.zj.entities.sys.entity.LoginUser;
import com.zj.entities.sys.entity.User;
import com.zj.sys.mapper.UserMapper;
import com.zj.sys.service.UserService;
import com.zj.sys.util.JwtTokenUtil;
import org.springframework.stereotype.Service;

/**
 * @author a1204
 * @version 1.0
 * @description: TODO
 * @date 2022/9/25 22:45
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * @param loginUser 登录实体类
     * @return com.zj.entity.Result<java.lang.String>
     * @description 用户登录，身份校验成功，返回值Result的data中返回token
     * @author 赵健
     * @date 2022/9/25 22:46
     */
    @Override
    public Result<String> clientLogin(LoginUser loginUser) {
        Result<String> result = new Result<>();
        User user = userMapper.getLoginUser(loginUser);
        if (user != null) {
            TokenUser tokenUser = new TokenUser();
            tokenUser.setId(user.getId());
            tokenUser.setRoleId(user.getRoleId());
            tokenUser.setUsername(user.getName());
            tokenUser.setUnitId(user.getSchoolId());
            String token = JwtTokenUtil.generateToken(tokenUser);
            result.setData(token);
            result.setResultEnum(StateEnum.SUCCESS);
        } else {
            result.setResultEnum(StateEnum.LOGIN_FAILED);
        }
        return result;
    }

    @Override
    public Result updateUser(UserDto userDto) {
        Result result = new Result();
        int i = userMapper.updateUser(userDto);
        if (i > 0) {
            result.setResultEnum(StateEnum.SUCCESS);
        } else {
            result.setResultEnum(StateEnum.FAILED);
        }
        return result;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            user = userMapper.getUserByUsername(username);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
