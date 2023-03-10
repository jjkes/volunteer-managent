package com.zj.school.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/10 22:42
 */
@Data
public class Course {
    private String id;
    private String name;
    private Teacher teacher;
    private Set<Sc> sc;

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sc=" + sc +
                '}';
    }
}
