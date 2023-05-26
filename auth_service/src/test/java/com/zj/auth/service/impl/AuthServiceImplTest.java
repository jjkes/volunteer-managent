package com.zj.auth.service.impl;

import com.zj.auth.AuthServiceApplication;
import com.zj.auth.service.AuthService;
import com.zj.common.config.Result;
import com.zj.common.exception.MyAuthException;

import com.zj.entities.sys.entity.LoginUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {AuthServiceApplication.class})
@RunWith(SpringRunner.class)
public class AuthServiceImplTest {
    @Autowired
    private AuthService authService;
    @Test
    public void testGetAuthToken() {
        try {
            ResponseEntity<Result> admin = authService.getAuthToken(new LoginUser("admin", "123123", "123123", "123123"));
            System.err.println(admin);
        } catch (MyAuthException e) {
            System.err.println(e.getMessage());
        }
    }
}