package com.zj.entities.sys.entity;


import com.zj.entities.sys.util.ShaUtil;

import javax.validation.constraints.NotEmpty;

/**
 * @author a1204
 * @version 1.0
 * @description: TODO
 * @date 2022/9/25 16:02
 */
public class LoginUser {

    /**
    * 用户id
    */
//    @Size(min = 0, max =10,message = "输出参数错误")
    private String Id;
    /**
    * 用户名
    */
    private String name;
    /**
    * 登录账号
    */
    @NotEmpty(message = "账号不能为空")
    private String account;
    /**
    * 用户密码
    */
    @NotEmpty(message = "密码不能为空")
//    @Size(min = 6,max = 12,message = "密码长度最长为")
    private String password;
    /**
    * 验证码
    */
//    @NotEmpty(message = "验证码不能为空")
    private String verifyCode;

    @NotEmpty(message = "随机验证码不能为空")
    private String randomCode;
    /**
    * 用户host
    */
    private String host;
    /**
    * 用户权限id
    */
    private String roleId;

    /**
     * 学校Id
     */
    private String schoolId;

    public LoginUser(String account, String password, String verifyCode, String randomStr) {
        this.account = account;
        this.password = ShaUtil.decrypt(password);
        this.verifyCode = verifyCode;
        this.randomCode = randomStr;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public String getHost() {
        return host;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public String getRandomCode(){
        return randomCode;
    }
}
