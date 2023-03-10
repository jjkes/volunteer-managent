package com.zj.school.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/10 17:27
 */
@Data
public class Student {
    private String id;
    private String name;
    private LocalDateTime age;
    private String sex;
    private Set<Sc> sc;
}
