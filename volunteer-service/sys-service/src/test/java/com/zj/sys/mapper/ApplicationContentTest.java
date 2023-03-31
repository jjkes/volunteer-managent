package com.zj.sys.mapper;

import com.zj.sys.SysApplication;
import com.zj.sys.controller.MenuController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/12 21:16
 */
@SpringBootTest(classes = {SysApplication.class})
public class ApplicationContentTest{
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Test
    public void test(){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("sdfsdf","sdfsdf");
        System.err.println(session.getAttribute("sdfsdf"));
//        MenuController menuController = (MenuController)applicationContext.getBean("menuController");
    }



}
