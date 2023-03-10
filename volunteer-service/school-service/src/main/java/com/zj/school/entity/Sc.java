package com.zj.school.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/10 22:40
 */
@Data
public class Sc implements Serializable{
    private static final long serialVersionUID = 1L;
    private String sid;
    private String cid;
    private Integer score;
    private Student student;
    private Course course;

//    @Override
//    public String toString() {
//        return "Sc{" +
//                "id=" + id +
//                ", sid='" + sid + '\'' +
//                ", cid='" + cid + '\'' +
//                ", score=" + score +
//                '}';
//    }
}
