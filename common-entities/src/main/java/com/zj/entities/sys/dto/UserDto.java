package com.zj.entities.sys.dto;


import com.zj.entities.sys.dto.insert.Insert;
import com.zj.entities.sys.dto.update.Update;
import com.zj.entities.sys.util.ShaUtil;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;


public class UserDto {
    @NotEmpty(message = "用户编号不能为空" ,groups = Update.class)
    @Null(message = "表单中不能填入用户ID",groups = Insert.class)
    private String id;
    @NotEmpty(message = "姓名不能为空",groups = Insert.class)
    private String name;
    private String alias;
    @NotEmpty(message = "账号不能为空",groups = Update.class)
    private String account;
    @NotEmpty(message = "密码不能为空",groups = Update.class)
    private String password;
    @NotEmpty(message = "邮箱号不能为空",groups = Update.class)
    private String email;
    @NotEmpty(message = "性别不能为空",groups = Update.class)
    private Integer sex;
    @NotEmpty(message = "手机号不能为空",groups = Update.class)
    private String tel;
    @NotEmpty(message = "角色ID不能为空",groups = Update.class)
    private String roleId;
    private String schoolId;
    private LocalDateTime updatePasswordTime;
    private Integer passwordErrorTimes;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void setUpdatePasswordTime(LocalDateTime updatePasswordTime) {
        this.updatePasswordTime = updatePasswordTime;
    }

    public void setPasswordErrorTimes(Integer passwordErrorTimes) {
        this.passwordErrorTimes = passwordErrorTimes;
    }

    public void setPassword(String password) {
        this.password = password;
        this.password = ShaUtil.decrypt(password);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getSex() {
        return sex;
    }

    public String getTel() {
        return tel;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public LocalDateTime getUpdatePasswordTime() {
        return updatePasswordTime;
    }

    public Integer getPasswordErrorTimes() {
        return passwordErrorTimes;
    }
}
