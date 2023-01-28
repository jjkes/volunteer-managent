package com.zj.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import com.zj.config.BaseController;
import com.zj.config.ControllerUtils;
import com.zj.entity.LoginUser;
import com.zj.entity.Result;
import com.zj.enums.CommonEnum;
import com.zj.enums.StateEnum;
import com.zj.sys.dto.TokenUser;
import com.zj.sys.dto.UserDto;
import com.zj.sys.dto.update.Update;
import com.zj.sys.service.UserService;
import com.zj.sys.util.RedisUtils;
import com.zj.sys.util.VerifyImgUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author a1204
 * @version 1.0
 * @description: TODO
 * @date 2022/9/25 16:44
 */
@BaseController(value = "/sys/user/")
public class UserController extends ControllerUtils {
    private final UserService userService;
    private final RedisUtils redisUtils;


    public UserController(RedisUtils redisUtils, UserService userService) {
        this.redisUtils = redisUtils;
        this.userService = userService;
    }

    /**
     * @description: 获取登录验证码
     * @param: [randomCode, request, response]
     * @return: com.alibaba.fastjson.JSONObject
     * @author: 赵健
     * @date: 2023/1/11 15:55
     */
    @GetMapping(value = "getVerifyImg")
    public JSONObject getVerifyImg(String randomCode, HttpServletResponse response) {
        Result result = new Result();
        // 验证随机码不为空
        boolean nullOrEmpty = StringUtils.isNullOrEmpty(randomCode);
        if (nullOrEmpty) {
             result.setResultEnum(StateEnum.VERIFY_RANDOM_STR_IS_NULL);
             return result.toJSON();
        }
        String imgCode = VerifyImgUtil.getImgCode(randomCode);
        BufferedImage textImage = VerifyImgUtil.getTextImage(imgCode);
        redisUtils.set(randomCode,imgCode);
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        try (ServletOutputStream outputStream = response.getOutputStream();) {
            ImageIO.write(textImage, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isOpenVerify;
    @Value("${volunteer.is-open-verify}")
    private void setIsOpenVerify(boolean b){
        isOpenVerify = b;
    }

    /**
     * @param loginUser
     * @return javax.xml.transform.Result
     * @description 登录系统
     * @author 赵健
     * @date 2022/9/25 16:55
     */
    @PostMapping(value = "login")
    public JSONObject login(@RequestBody LoginUser loginUser, HttpServletResponse response) {
        String s = redisUtils.get(loginUser.getRandomCode());
        System.err.println(s);
//        s=null;
        if(isOpenVerify){
            if (!loginUser.getVerifyCode().equalsIgnoreCase(s)) {
                return new Result<>().setResultEnum(StateEnum.VERIFY_RANDOM_ERROR).toJSON();
            }
        }

        Result<String> login = userService.clientLogin(loginUser);
        if (login.getResultEnum().getCode() == 0) {
            response.setHeader("AuthorizationToken", login.getData());
            response.setHeader("Access-Control-Expose-Headers", "AuthorizationToken");
        }
        return login.toJSON();
    }

    /**
     * @description: 获取token中存储的username
     * @return: com.alibaba.fastjson.JSONObject
     * @param: [request]
     * @author 赵健
     * @date 2023/1/20 17:43
     */

    @GetMapping("getUserName")
    public JSONObject getUserName(HttpServletRequest request) {
        TokenUser tokenUser = solveToken(request);
        String username = tokenUser.getUsername();
        Result<String> objectResult = new Result<>();
        objectResult.setData(username);
        return objectResult.toJSON();
    }

    @GetMapping("changeUserRole")
    public JSONObject changeUserRole(HttpServletRequest request, @Validated(Update.class ) UserDto userDto){
        TokenUser tokenUser = solveToken(request);
        String roleId = tokenUser.getRoleId();
        // TODO 验证是否符合权限
        Result result = userService.updateUser(userDto);
        return result.toJSON();
    }
}
