package com.zj.school.controller;

import com.zj.school.config.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/1 22:12
 */
@BaseController("/school/")
public class SchoolController {
    @GetMapping("insert")
    public String insert(){
        System.err.println("调用成功");
        return "调用成功";
    }
}
