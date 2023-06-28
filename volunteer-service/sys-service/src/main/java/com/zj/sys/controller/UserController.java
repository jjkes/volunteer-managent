package com.zj.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zj.common.annotations.Authentication;
import com.zj.common.constant.StateEnum;
import com.zj.common.utils.RedisUtil;
import com.zj.sys.config.BaseController;
import com.zj.sys.config.ControllerUtils;
import com.zj.sys.config.Result;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.dto.UserDto;
import com.zj.entities.sys.dto.update.Update;
import com.zj.entities.sys.entity.LoginUser;
import com.zj.entities.sys.entity.User;
import com.zj.sys.service.UserService;
import com.zj.sys.util.VerifyImgUtil;
import com.zj.sys.webService.SchoolService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author a1204
 * @version 1.0
 * @description: TODO
 * @date 2022/9/25 16:44
 */
@BaseController(value = "/sys/user/")
public class UserController extends ControllerUtils {
    private static boolean isOpenVerify;
    private final UserService userService;

    private final RedisUtil redisUtil;
    private final SchoolService schoolService;

    public UserController(UserService userService, RedisUtil redisUtil, SchoolService schoolService) {
        this.userService = userService;
        this.redisUtil = redisUtil;
        this.schoolService = schoolService;
    }

    /**
     * @description: 获取登录验证码
     * @param: [randomCode, request, response]
     * @return: com.alibaba.fastjson.JSONObject
     * @author: 赵健
     * @date: 2023/1/11 15:55
     */
    @GetMapping(value = "getVerifyImg")
    @Authentication(type = 0,description = "获取验证码的系统不需要验证权限")
    public JSONObject getVerifyImg(String randomCode, HttpServletResponse response) {
        Result result = new Result();
        // 验证随机码不为空
//        boolean nullOrEmpty = StringUtils.isNullOrEmpty(randomCode);
        boolean nullOrEmpty = true;
        if (nullOrEmpty) {
            result.setResultEnum(StateEnum.VERIFY_RANDOM_STR_IS_NULL);
            return result.toJSON();
        }
        String imgCode = VerifyImgUtil.getImgCode(randomCode);
        BufferedImage textImage = VerifyImgUtil.getTextImage(imgCode);
        redisUtil.setStringValue(randomCode, imgCode);
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

    @Value("${volunteer.is-open-verify}")
    private void setIsOpenVerify(boolean b) {
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
    @Authentication(type = 0,description = "登录系统不需要验证权限")
    public JSONObject login(@RequestBody LoginUser loginUser, HttpServletResponse response) {
        String s = redisUtil.getStringValue(loginUser.getRandomCode());
        System.err.println(s);
//        s=null;
        if (isOpenVerify) {
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

    @HystrixCommand(fallbackMethod = "returnErrorMsgForGetUserName", commandProperties = {@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "30")
//            @HystrixProperty(name = "")
    })
    @GetMapping("getUserName")
    public JSONObject getUserName(HttpServletRequest request) {
        TokenUser tokenUser = solveToken(request);
        String username = tokenUser.getUsername();
        Result<String> objectResult = new Result<>();
        objectResult.setData(username);
        System.err.println(schoolService.schoolInsert());
//        int a=1/0;
        return objectResult.toJSON();
    }

    public JSONObject returnErrorMsgForGetUserName(HttpServletRequest request) {
        System.err.println("触发熔断");
        return new Result<>().setResultEnum(StateEnum.VERIFY_RANDOM_ERROR).toJSON();
    }

    @GetMapping("changeUserRole")
    public JSONObject changeUserRole(HttpServletRequest request, @Validated(Update.class) UserDto userDto) {
        TokenUser tokenUser = solveToken(request);
        String roleId = tokenUser.getRoleId();
        // TODO 验证是否符合权限
        Result result = userService.updateUser(userDto);
        return result.toJSON();
    }

    @GetMapping("upLoadFile")
    public String upLoadFile(MultipartFile file) {
        File file1 = new File("C:\\Users\\a1204\\Desktop\\git\\git.text");
        try (OutputStream opt = new FileOutputStream(file1)) {
            file.transferTo(file1);
            opt.flush();
            byte[] bytes = file.getBytes();
            opt.write(bytes);
            opt.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getContentType();
    }

    @GetMapping(value = "getUserByUsername")
    public User getUserByUsername(String username) {
        System.err.println("dssfadf");
        User user = userService.getUserByUsername(username);
        return user;
    }
}
