package com.zj.school.mapper;

import com.zj.school.entity.School;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/1 21:37
 */
@Mapper
public interface SchoolMapper {
    int insertSchool(School school);
}
