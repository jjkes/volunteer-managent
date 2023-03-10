package com.zj.auth.utls;

import com.zj.auth.AuthServiceApplication;
import com.zj.auth.service.AuthService;
import com.zj.auth.webService.SysService;
import com.zj.common.config.Result;
import com.zj.sys.entity.LoginUser;
import com.zj.sys.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/4 23:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthServiceApplication.class})
public class LoginTest{
    @Autowired
    private SysService sysService;
    @Autowired
    private AuthService authService;

    @Test
    public void LoginTest() {
        ResponseEntity<Result> admin = authService.getAuthToken(new LoginUser("admin", "123123", "123", "123"));
        System.err.println(admin);
    }


    @Test
    public void login() {
        User admin = sysService.getUserByUsername("admin");
        System.err.println(admin);
    }
}
