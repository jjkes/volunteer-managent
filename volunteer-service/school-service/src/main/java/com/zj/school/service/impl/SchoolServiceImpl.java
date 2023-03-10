package com.zj.school.service.impl;

import com.zj.school.entity.School;
import com.zj.school.mapper.SchoolMapper;
import com.zj.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/1 22:02
 */

public class SchoolServiceImpl implements SchoolService {

    private final SchoolMapper schoolMapper;

    @Autowired
    public SchoolServiceImpl(SchoolMapper schoolMapper) {
        this.schoolMapper = schoolMapper;
    }


    @Override
    public String insertSchool(School school) {
        schoolMapper.insertSchool(school);
        return "123";
    }
}
