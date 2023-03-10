package com.zj.school.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/10 22:43
 */
@Data
public class Teacher {
    private String id;
    private String name;
    private Set<Course> course;
}
